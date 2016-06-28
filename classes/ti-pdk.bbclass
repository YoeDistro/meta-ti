require recipes-ti/includes/ti-paths.inc
require recipes-ti/includes/ti-staging.inc

inherit perlnative

DEPENDS = "ti-xdctools ti-cg-xml-native ti-sysbios common-csl-ip-rtos libxml-simple-perl-native gcc-arm-none-eabi-native ti-cgt6x-native ti-pdk-build-rtos"

DEPENDS_append_omap-a15 = " ti-ccsv6-native"
DEPENDS_remove_ti33x = "ti-cgt6x-native"
DEPENDS_remove_ti43x = "ti-cgt6x-native"

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
export C6X_GEN_INSTALL_PATH = "${STAGING_DIR_NATIVE}/usr/share/ti/cgt-c6x"

export ROOTDIR = "${B}"
export BIOS_INSTALL_PATH = "${SYSBIOS_INSTALL_DIR}"
export XDC_INSTALL_PATH = "${XDC_INSTALL_DIR}"
export PDK_INSTALL_PATH = "${PDK_INSTALL_DIR}/packages"

export XDCPATH = "${XDC_INSTALL_DIR}/packages;${SYSBIOS_INSTALL_DIR}/packages;${PDK_INSTALL_DIR}/packages"
export SECTTI="perl ${CG_XML_INSTALL_DIR}/ofd/sectti.pl"

XDCMAKE ?= "1"
LIMSOCS ?= ""
LIMBOARDS ?= ""

PARALLEL_XDC = "--jobs=${BB_NUMBER_THREADS}"

do_configure() {
    BUILD_DIR=${B}/`get_build_dir_bash`

    mkdir -p ${BUILD_DIR}
    cp -r ${S}/* ${BUILD_DIR}

    if [ "${XDCMAKE}" == "1" ]
    then
        cd ${BUILD_DIR}

        sed -i "s/\ \"\.\\\\\\\\\"\ +//" src/Module.xs
        find -name "*.xs" -exec sed -i "s/ofd6x\.exe/ofd6x/" {} \;
        find -name "*.xs" -exec sed -i "s/sectti\.exe/sectti/" {} \;
        find -name "*.xs" -exec sed -i "/\.chm/d" {} \;
        find -name "*.xs" -exec sed -i "s/pasm\_dos/pasm\_linux/" {} \;
    fi
}

do_compile() {

    if [ "${XDCMAKE}" == "1" ]
    then
        ${XDC_INSTALL_DIR}/xdc clean ${PARALLEL_XDC} -PR .
        ${XDC_INSTALL_DIR}/xdc all ${PARALLEL_XDC} XDCARGS="${XDCARGS}" ROOTDIR="${ROOTDIR}" -PR .
        ${XDC_INSTALL_DIR}/xdc release XDCARGS="${XDCARGS}" -PR .
    else
        BUILD_DIR=${B}/`get_build_dir_bash`
        cd ${BUILD_DIR}

        make release LIMIT_SOCS="${LIMSOCS}" LIMIT_BOARDS="${LIMBOARDS}"
    fi
}

do_install () {
    install -d ${D}${PDK_INSTALL_DIR_RECIPE}/packages
    find -name "*.tar" -exec tar xf {} -C ${D}${PDK_INSTALL_DIR_RECIPE}/packages \;
    
    if [ "${PDK_COMP_LINK_TEXT}" != "" ]
    then
        install -d ${D}${PDK_INSTALL_DIR_RECIPE}/packages/.extras/doc
        create_doc_link
    fi
}

FILES_${PN} += "${PDK_INSTALL_DIR_RECIPE}/packages"
