require recipes-ti/includes/ti-paths.inc
require recipes-ti/includes/ti-staging.inc

inherit perlnative

DEPENDS = "ti-xdctools-native ti-cg-xml-native ti-sysbios common-csl-ip-rtos libxml-simple-perl-native gcc-arm-none-eabi-native ti-cgt6x-native ti-cgt-pru-native ti-pdk-build-rtos doxygen-native"

DEPENDS_append_omap-a15 = " ti-cgt-arm-native"
DEPENDS_remove_ti33x = "ti-cgt6x-native"
DEPENDS_remove_ti43x = "ti-cgt6x-native"
DEPENDS_append_omapl1 = " ti-cgt-arm-native"
DEPENDS_remove_k3 = "gcc-arm-none-eabi-native ti-cgt6x-native"
DEPENDS_append_k3 = " ti-cgt-arm-native gcc-linaro-baremetal-aarch64-native"

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

# HTML hyperlink text
PDK_COMP_LINK_TEXT ?= ""

DOC_FILE = "API_Documentation_${PN}.html"

create_doc_link () {
    PDK_COMP_DIR=`get_build_dir_bash`

    echo "<a href=\"${PDK_COMP_DIR}/docs/doxygen/html/index.html\">${PDK_COMP_LINK_TEXT}</a>" >> ${D}${PDK_INSTALL_DIR_RECIPE}/packages/.extras/doc/${DOC_FILE}
}

get_build_dir_bash() {
  if [ -f ${S}/package.xdc ]
  then
    grep '^package' ${S}/package.xdc | sed -e 's|\[.*$||' | awk '{ print $2 }' | sed -e 's|\.|/|g'
  else
    echo ${S}
    return 1
  fi
}

export CROSS_TOOL_PRFX="arm-none-eabi-"
export TOOLCHAIN_PATH_A8 = "${GCC_ARM_NONE_TOOLCHAIN}"
export TOOLCHAIN_PATH_A9 = "${GCC_ARM_NONE_TOOLCHAIN}"
export TOOLCHAIN_PATH_A15 = "${GCC_ARM_NONE_TOOLCHAIN}"
export TOOLCHAIN_PATH_M4 = "${M4_TOOLCHAIN_INSTALL_DIR}"
export TOOLCHAIN_PATH_Arm9 = "${M4_TOOLCHAIN_INSTALL_DIR}"
export C6X_GEN_INSTALL_PATH = "${STAGING_DIR_NATIVE}/usr/share/ti/cgt-c6x"
export TOOLCHAIN_PATH_EVE = "${STAGING_DIR_NATIVE}/usr/share/ti/cgt-arp32"
export CL_PRU_INSTALL_PATH = "${TI_CGT_PRU_INSTALL_DIR}"
export TOOLCHAIN_PATH_GCC_ARCH64 = "${GCC_LINARO_BAREMETAL_AARCH64_TOOLCHAIN}"
export TOOLCHAIN_PATH_R5 = "${M4_TOOLCHAIN_INSTALL_DIR}"

export ROOTDIR = "${B}"
export BIOS_INSTALL_PATH = "${SYSBIOS_INSTALL_DIR}"
export XDC_INSTALL_PATH = "${XDC_INSTALL_DIR}"
export PDK_INSTALL_PATH = "${PDK_INSTALL_DIR}/packages"

export XDCPATH = "${XDC_INSTALL_DIR}/packages;${SYSBIOS_INSTALL_DIR}/packages;${PDK_INSTALL_DIR}/packages"
export SECTTI="perl ${CG_XML_INSTALL_DIR}/ofd/sectti.pl"

TI_PDK_XDCMAKE ?= "1"

# By default, only build the cores with available toolchains
TI_PDK_LIMIT_CORES ?= "a15_0 ipu1_0 ipu1_1 ipu2_0 ipu2_1 c66x c66xdsp_1 c66xdsp_2 arm9_0 c674x a9host a8host pru_0 pru_1"
TI_PDK_LIMIT_SOCS ?= ""
TI_PDK_LIMIT_BOARDS ?= ""
TI_PDK_MAKE_TARGET ?= "release"
TI_PDK_EXTRA_MAKE ?= ""
TI_PDK_DOXYGEN_SUPPORT ?= "1"

TI_PDK_XDC_ARGS ?= "${TI_PDK_LIMIT_SOCS}"

PARALLEL_XDC = "--jobs=${BB_NUMBER_THREADS}"
PARALLEL_MAKE = ""

def get_doxygen_support(d):
    if d.getVar('TI_PDK_DOXYGEN_SUPPORT') == '1':
        return ''
    return 'DOXYGEN_SUPPORT=no'

EXTRA_OEMAKE = " \
    LIMIT_SOCS="${TI_PDK_LIMIT_SOCS}" \
    LIMIT_BOARDS="${TI_PDK_LIMIT_BOARDS}" \
    LIMIT_CORES="${TI_PDK_LIMIT_CORES}" \
    ${TI_PDK_EXTRA_MAKE} \
    ${@get_doxygen_support(d)} \
"

do_configure() {
    BUILD_DIR=${B}/`get_build_dir_bash`

    mkdir -p ${BUILD_DIR}
    cp -r ${S}/* ${BUILD_DIR}

    if [ "${TI_PDK_XDCMAKE}" == "1" ]
    then
        cd ${BUILD_DIR}

        sed -i "s/\ \"\.\\\\\\\\\"\ +//" src/Module.xs
        find -name "*.xs" -exec sed -i "s/ofd6x\.exe/ofd6x/" {} \;
        find -name "*.xs" -exec sed -i "s/sectti\.exe/sectti/" {} \;
        find -name "*.xs" -exec sed -i "/\.chm/d" {} \;
        find -name "*.xs" -exec sed -i "s/pasm\_dos/pasm\_linux/" {} \;

        cd ${B}
        ${XDC_INSTALL_DIR}/xdc clean ${PARALLEL_XDC} -PR .
    else
        if [ "${CLEANBROKEN}" != "1" ]
        then
            cd ${BUILD_DIR}
            oe_runmake clean
            cd "${B}"
        fi
    fi

}

do_compile() {

    if [ "${TI_PDK_XDCMAKE}" == "1" ]
    then
        ${XDC_INSTALL_DIR}/xdc all ${PARALLEL_XDC} XDCARGS="${TI_PDK_XDC_ARGS}" ROOTDIR="${ROOTDIR}" -PR .
        ${XDC_INSTALL_DIR}/xdc release XDCARGS="${TI_PDK_XDC_ARGS}" -PR .
    else
        BUILD_DIR=${B}/`get_build_dir_bash`
        cd ${BUILD_DIR}

        oe_runmake ${TI_PDK_MAKE_TARGET}
    fi
}

do_install () {
    install -d ${D}${PDK_INSTALL_DIR_RECIPE}/packages
    find -name "*.tar" -exec tar xf {} --no-same-owner -C ${D}${PDK_INSTALL_DIR_RECIPE}/packages \;
    
    if [ "${PDK_COMP_LINK_TEXT}" != "" ]
    then
        install -d ${D}${PDK_INSTALL_DIR_RECIPE}/packages/.extras/doc
        create_doc_link
    fi
}

FILES_${PN} += "${PDK_INSTALL_DIR_RECIPE}/packages"
