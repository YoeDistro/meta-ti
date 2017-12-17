require edma3-lld.inc
require recipes-ti/includes/ti-paths.inc

PR = "${INC_PR}.1"

DEPENDS = "ti-sysbios ti-xdctools-native gcc-arm-none-eabi-native ti-cgt-arm-native ti-cgt6x-native"

COMPATIBLE_MACHINE = "ti33x|ti43x|omap-a15|keystone|c66x|omapl1"
PACKAGE_ARCH = "${MACHINE_ARCH}"

PLATFORMLIST = ""
TARGETLIST = ""

PLATFORMLIST_ti33x = " \
        am335x-evm \
"

TARGETLIST_ti33x = " \
        a8 \
"

PLATFORMLIST_ti43x = " \
        am437x-evm \
"

TARGETLIST_ti43x = " \
        arm9 \
"

PLATFORMLIST_omap-a15 = " \
        tda2xx-evm \
"

TARGETLIST_omap-a15 = " \
        66 \
        a15 \
        m4 \
"

PLATFORMLIST_keystone = " \
        tci6636k2h-evm \
        tci6638k2k-evm \
        tci6630k2l-evm \
        c66ak2e-evm \
        tci66ak2g02-evm \
"

TARGETLIST_keystone = " \
        66 \
        a15 \
"

PLATFORMLIST_append_dra7xx-evm = " \
        tda3xx-evm \
"

TARGETLIST_c66x = " \
        66 \
"

PLATFORMLIST_c665x-evm = " \
        c6657-evm \
"

PLATFORMLIST_c667x-evm = " \
        c6678-evm \
"

PLATFORMLIST_omapl1 = " \
        omapl137-evm \
        omapl138-evm \
"

TARGETLIST_omapl1 = " \
        arm9 \
        674 \
"

EXTRA_OEMAKE_remove = "TARGET=66"
FORMAT="ELF"

S = "${WORKDIR}/git"

PARALLEL_MAKE = ""

export C6X_GEN_INSTALL_PATH = "${STAGING_DIR_NATIVE}/usr"
export XDCCGROOT = "${STAGING_DIR_NATIVE}/usr/share/ti/cgt-c6x"
export CGTOOLS = "${XDCCGROOT}"
export CGTOOLS_ELF = "${XDCCGROOT}"
export CODEGEN_PATH_DSP = "${XDCCGROOT}"
export CODEGEN_PATH_DSPELF = "${XDCCGROOT}"
export TMS470_CGTOOLS = "${M4_TOOLCHAIN_INSTALL_DIR}"
export UTILS_INSTALL_DIR = "${XDC_INSTALL_DIR}"
export XDCPATH = "${XDCCGROOT}/include;${XDC_INSTALL_DIR}/packages;${SYSBIOS_INSTALL_DIR}/packages"
export PATH := "${XDC_INSTALL_DIR}:${PATH}"
export ROOTDIR="${S}"
export INTERNAL_SW_ROOT="${S}"
export CROSSCC="${TARGET_PREFIX}gcc"
export CROSSAR="${TARGET_PREFIX}ar"
export CROSSLNK="${TARGET_PREFIX}gcc"

do_configure () {
    rm -rf ${S}/examples/edma3_driver/evmTCI6614
    rm -rf ${S}/examples/edma3_driver/evmTCI6614BE
    sed -i -e "s|_config.bld|config.bld|g" ${S}/makerules/env.mk
    sed -i -e "s|^edma3_lld_PATH =.*$|edma3_lld_PATH = ${S}|g" ${S}/makerules/env.mk
    sed -i -e "s|^CODEGEN_PATH_M3 =.*$|CODEGEN_PATH_M3 = ${M4_TOOLCHAIN_INSTALL_DIR}|g" ${S}/makerules/env.mk
    sed -i -e "s|^CODEGEN_PATH_M4 =.*$|CODEGEN_PATH_M4 = ${M4_TOOLCHAIN_INSTALL_DIR}|g" ${S}/makerules/env.mk
    sed -i -e "s|^CODEGEN_PATH_A8 =.*$|CODEGEN_PATH_A8 = ${M4_TOOLCHAIN_INSTALL_DIR}|g" ${S}/makerules/env.mk
    sed -i -e "s|^CODEGEN_PATH_A8_GCC =.*$|CODEGEN_PATH_A8_GCC = ${GCC_ARM_NONE_TOOLCHAIN}|g" ${S}/makerules/env.mk
    sed -i -e "s|^CODEGEN_PATH_ARM9 =.*$|CODEGEN_PATH_ARM9 = ${M4_TOOLCHAIN_INSTALL_DIR}|g" ${S}/makerules/env.mk
    sed -i -e "s|^CODEGEN_PATH_A9_GCC =.*$|CODEGEN_PATH_A9_GCC = ${GCC_ARM_NONE_TOOLCHAIN}|g" ${S}/makerules/env.mk
    sed -i -e "s|^CODEGEN_PATH_A15 =.*$|CODEGEN_PATH_A15 = ${GCC_ARM_NONE_TOOLCHAIN}|g" ${S}/makerules/env.mk
    sed -i -e "s|^CODEGEN_PATH_A15_GCC =.*$|CODEGEN_PATH_A15_GCC = ${GCC_ARM_NONE_TOOLCHAIN}|g" ${S}/makerules/env.mk
    sed -i -e "s|^UTILS_INSTALL_DIR =.*$|UTILS_INSTALL_DIR = ${XDC_INSTALL_DIR}|g" ${S}/makerules/env.mk
    sed -i -e "s|^bios_PATH =.*$|bios_PATH = ${SYSBIOS_INSTALL_DIR}|g" ${S}/makerules/env.mk
    sed -i -e "s|^xdc_PATH =.*$|xdc_PATH = ${XDC_INSTALL_DIR}|g" ${S}/makerules/env.mk
    sed -i -e "s|^CODEGEN_PATH_DSP =.*$|CODEGEN_PATH_DSP = ${XDCCGROOT}|g" ${S}/makerules/env.mk
    sed -i -e "s|^CODEGEN_PATH_DSPELF =.*$|CODEGEN_PATH_DSPELF = ${XDCCGROOT}|g" ${S}/makerules/env.mk

    cd ${S}/packages
    ${XDC_INSTALL_DIR}/xdc .interfaces -PR .
}

do_compile () {
    cd ${S}/packages
    for p in ${PLATFORMLIST}
    do
        for t in ${TARGETLIST}
        do
            make PLATFORM=${p} TARGET=${t} FORMAT=${FORMAT}
        done
    done
}

do_install () {
    CP_ARGS="-Prf --preserve=mode,timestamps --no-preserve=ownership"
    install -d ${D}${EDMA3_LLD_INSTALL_DIR_RECIPE}
    cp ${CP_ARGS} ${S}/* ${D}${EDMA3_LLD_INSTALL_DIR_RECIPE}
}

INSANE_SKIP_${PN}-dev = "arch ldflags file-rdeps"

ALLOW_EMPTY_${PN} = "1"
FILES_${PN}-dev += "${EDMA3_LLD_INSTALL_DIR_RECIPE}"
