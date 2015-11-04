require edma3-lld.inc
require recipes-ti/includes/ti-paths.inc
require recipes-ti/includes/ti-staging.inc

PR = "${INC_PR}.0"

DEPENDS = "ti-cgt6x-native ti-sysbios ti-xdctools"

COMPATIBLE_MACHINE = "dra7xx|keystone"
PACKAGE_ARCH = "${MACHINE_ARCH}"

PLATFORMLIST = ""
PLATFORMLIST_keystone = " \
        tci6636k2h-evm \
        tci6638k2k-evm \
        tci6630k2l-evm \
        c66ak2e-evm \
"

PLATFORMLIST_dra7xx = " \
        tda2xx-evm \
        dra72x-evm \
        am571x-evm \
        am572x-evm \
"

S = "${WORKDIR}/git"

PARALLEL_MAKE = ""

export C6X_GEN_INSTALL_PATH = "${STAGING_DIR_NATIVE}/usr"
export XDCCGROOT = "${STAGING_DIR_NATIVE}/usr/share/ti/cgt-c6x"
export CGTOOLS = "${XDCCGROOT}"
export CGTOOLS_ELF = "${XDCCGROOT}"
export CODEGEN_PATH_DSP = "${XDCCGROOT}"
export CODEGEN_PATH_DSPELF = "${XDCCGROOT}"
export TMS470_CGTOOLS = "${XDCCGROOT}"
export UTILS_INSTALL_DIR = "${XDC_INSTALL_DIR}"
export XDCPATH = "${XDCCGROOT}/include;${XDC_INSTALL_DIR}/packages;${SYSBIOS_INSTALL_DIR}/packages"
export PATH := "${XDC_INSTALL_DIR}:${PATH}"
export ROOTDIR="${S}"
export INTERNAL_SW_ROOT="${S}"
export CROSSCC="${TARGET_PREFIX}gcc"
export CROSSAR="${TARGET_PREFIX}ar"
export CROSSLNK="${TARGET_PREFIX}gcc"


EXTRA_OEMAKE += " -C ${S}/packages TARGET=66 FORMAT=ELF"

do_configure () {
    sed -i -e "s|^UTILS_INSTALL_DIR =.*$|UTILS_INSTALL_DIR = ${XDC_INSTALL_DIR}|g" ${S}/makerules/env.mk
    sed -i -e "s|^bios_PATH =.*$|bios_PATH = ${SYSBIOS_INSTALL_DIR}|g" ${S}/makerules/env.mk
    sed -i -e "s|^xdc_PATH =.*$|xdc_PATH = ${XDC_INSTALL_DIR}|g" ${S}/makerules/env.mk
    sed -i -e "s|^CODEGEN_PATH_DSP =.*$|CODEGEN_PATH_DSP = ${XDCCGROOT}|g" ${S}/makerules/env.mk
    sed -i -e "s|^CODEGEN_PATH_DSPELF =.*$|CODEGEN_PATH_DSPELF = ${XDCCGROOT}|g" ${S}/makerules/env.mk

    cd ${S}/packages
    ${XDC_INSTALL_DIR}/xdc .interfaces -PR .
}

do_compile () {
    for p in ${PLATFORMLIST}
    do
        oe_runmake PLATFORM=${p}
    done
}

do_install () {
    install -d ${D}${EDMA3_LLD_INSTALL_DIR_RECIPE}
    cp -pPrf ${S}/* ${D}${EDMA3_LLD_INSTALL_DIR_RECIPE}
}

INSANE_SKIP_${PN}-dev = "arch"

ALLOW_EMPTY_${PN} = "1"
FILES_${PN}-dev += "${EDMA3_LLD_INSTALL_DIR_RECIPE}"
