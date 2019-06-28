SUMMARY = "MCU Watchdog test firmware"
DESCRIPTION = "Firmware for the R5F core to test the detection of watchdog timeout events to trigger SOC reset though DMSC."

require recipes-bsp/common-csl-ip/common-csl-ip.inc
require recipes-ti/includes/ti-paths.inc

LIC_FILES_CHKSUM = "file://../../../COPYING.txt;md5=5857833e20836213677fac33f9aded21"

S = "${WORKDIR}/${CSL_GIT_DESTSUFFIX}/example/rti/rti_dwwdtest_app"

COMPATIBLE_MACHINE = "am65xx"
PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS = "ti-cgt-arm-native \
           ti-pdk-build-rtos \
           uart-lld-rtos \
           board-rtos \
           sciclient-rtos \
           udma-lld-rtos"

export PDK_INSTALL_PATH = "${PDK_INSTALL_DIR}/packages"
export TOOLCHAIN_PATH_R5 = "${M4_TOOLCHAIN_INSTALL_DIR}"

EXTRA_OEMAKE = "WDT_RESET=enable DEST_ROOT=${S}"

do_compile() {
    for soc in ${TI_PDK_LIMIT_SOCS}
    do
        for board in ${TI_PDK_LIMIT_BOARDS}
        do
            oe_runmake SOC=$soc BOARD=$board
        done
    done
}

do_install() {
    install -d ${D}${base_libdir}/firmware/rti_dwwdtest

    for board in ${TI_PDK_LIMIT_BOARDS}
    do
        install -d ${D}${base_libdir}/firmware/rti_dwwdtest/$board
        install -m 0644 csl_rti_dwwd_test_app/bin/$board/csl_rti_dwwd_test_app_mcu1_0_release.xer5f \
                        ${D}${base_libdir}/firmware/rti_dwwdtest/$board
    done
}

# Create separate package for each firmware so we can utilize
# update-alternatives.
PACKAGES =+ "${PN}-evm ${PN}-idk"
RDEPENDS_${PN} = "${PN}-evm ${PN}-idk"
ALLOW_EMPTY_${PN} = "1"

FILES_${PN}-evm = "${base_libdir}/firmware/rti_dwwdtest/am65xx_evm"
FILES_${PN}-idk = "${base_libdir}/firmware/rti_dwwdtest/am65xx_idk"

# We are packaging R5 firmware
INSANE_SKIP_${PN}-evm = "arch"
INSANE_SKIP_${PN}-idk = "arch"

# Configure update-alternatives as there may be other firmwares provided in an
# image.
inherit update-alternatives

# It might be nice to dynamically declare the following based on
# TI_PDK_LIMIT_BOARDS, but that is probably overkill.
ALTERNATIVE_${PN}-evm = "am65x-mcu-r5f0_0-fw"
ALTERNATIVE_${PN}-idk = "am65x-mcu-r5f0_0-fw"

ALTERNATIVE_LINK_NAME[am65x-mcu-r5f0_0-fw] = "${base_libdir}/firmware/am65x-mcu-r5f0_0-fw"

ALTERNATIVE_TARGET_${PN}-evm = "${base_libdir}/firmware/rti_dwwdtest/am65xx_evm/csl_rti_dwwd_test_app_mcu1_0_release.xer5f"
ALTERNATIVE_TARGET_${PN}-idk = "${base_libdir}/firmware/rti_dwwdtest/am65xx_idk/csl_rti_dwwd_test_app_mcu1_0_release.xer5f"

# Use a lesser priority than ipc example fw
# See meta-ti/recipes-ti/ipc/ti-ipc-rtos_git.bb
ALTERNATIVE_PRIORITY_${PN}-evm = "4"
ALTERNATIVE_PRIORITY_${PN}-idk = "3"
