SUMMARY = "TI Echo Test prebuilt binary firmware images"

LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://${THISDIR}/../../licenses/TI-TFL;md5=a1b59cb7ba626b9dbbcbf00f3fbc438a"

COMPATIBLE_MACHINE = "k3"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit update-alternatives

PLAT_SFX = ""
PLAT_SFX:j721e = "j721e"
PLAT_SFX:j7200 = "j7200"
PLAT_SFX:j721s2 = "j721s2"
PLAT_SFX:j784s4 = "j784s4"
PLAT_SFX:j722s = "j722s"
PLAT_SFX:j742s2 = "j742s2"
PLAT_SFX:am65xx = "am65xx"
PLAT_SFX:am64xx = "am64xx"
PLAT_SFX:am62xx = "am62xx"
PLAT_SFX:am62axx = "am62axx"
PLAT_SFX:am62dxx = "am62axx"
PLAT_SFX:am62pxx = "am62pxx"

FILESEXTRAPATHS:prepend := "${METATIBASE}/recipes-bsp/ti-sci-fw/files/:"
require recipes-bsp/ti-linux-fw/ti-linux-fw.inc

PV = "${CORESDK_RTOS_VERSION}"
PR = "${INC_PR}.1"

# Secure Build
inherit ti-secdev

IPC_FW_DIR = "ti-ipc/${PLAT_SFX}"

INSTALL_IPC_FW_DIR = "${nonarch_base_libdir}/firmware/${IPC_FW_DIR}"

MCU_1_0_FW =       "ipc_echo_test_mcu1_0_release_strip.xer5f"
MCU_1_1_FW =       "ipc_echo_test_mcu1_1_release_strip.xer5f"
MCU_2_0_FW =       "ipc_echo_test_mcu2_0_release_strip.xer5f"
MCU_2_1_FW =       "ipc_echo_test_mcu2_1_release_strip.xer5f"
MCU_3_0_FW =       "ipc_echo_test_mcu3_0_release_strip.xer5f"
MCU_3_1_FW =       "ipc_echo_test_mcu3_1_release_strip.xer5f"
MCU_4_0_FW =       "ipc_echo_test_mcu4_0_release_strip.xer5f"
MCU_4_1_FW =       "ipc_echo_test_mcu4_1_release_strip.xer5f"
C66_1_FW =         "ipc_echo_test_c66xdsp_1_release_strip.xe66"
C66_2_FW =         "ipc_echo_test_c66xdsp_2_release_strip.xe66"
C7X_1_FW =         "ipc_echo_test_c7x_1_release_strip.xe71"
C7X_1_FW:am62axx = "dsp_edgeai_c7x_1_release_strip.out"
C7X_2_FW =         "ipc_echo_test_c7x_2_release_strip.xe71"
C7X_3_FW =         "ipc_echo_test_c7x_3_release_strip.xe71"
C7X_4_FW =         "ipc_echo_test_c7x_4_release_strip.xe71"

IPC_FW_LIST = ""
IPC_FW_LIST:am65xx =  "${MCU_1_0_FW} ${MCU_1_1_FW}"
IPC_FW_LIST:am64xx =  "${MCU_1_0_FW} ${MCU_1_1_FW} ${MCU_2_0_FW} ${MCU_2_1_FW} ${MCU_3_0_FW}"
IPC_FW_LIST:am62xx =  "                            ${MCU_2_0_FW}"
IPC_FW_LIST:am62pxx = "                            ${MCU_2_0_FW}"
IPC_FW_LIST:am62axx = "                            ${MCU_2_0_FW}                                                                                               ${C7X_1_FW}"
IPC_FW_LIST:am62dxx = "                            ${MCU_2_0_FW}                                                                                               ${C7X_1_FW}"
IPC_FW_LIST:j721e =   "              ${MCU_1_1_FW} ${MCU_2_0_FW} ${MCU_2_1_FW} ${MCU_3_0_FW} ${MCU_3_1_FW}                             ${C66_1_FW} ${C66_2_FW} ${C7X_1_FW}"
IPC_FW_LIST:j7200 =   "              ${MCU_1_1_FW} ${MCU_2_0_FW} ${MCU_2_1_FW}"
IPC_FW_LIST:j721s2 =  "              ${MCU_1_1_FW} ${MCU_2_0_FW} ${MCU_2_1_FW} ${MCU_3_0_FW} ${MCU_3_1_FW}                                                     ${C7X_1_FW} ${C7X_2_FW}"
IPC_FW_LIST:j784s4 =  "              ${MCU_1_1_FW} ${MCU_2_0_FW} ${MCU_2_1_FW} ${MCU_3_0_FW} ${MCU_3_1_FW} ${MCU_4_0_FW} ${MCU_4_1_FW}                         ${C7X_1_FW} ${C7X_2_FW} ${C7X_3_FW} ${C7X_4_FW}"
IPC_FW_LIST:j722s =   "                            ${MCU_2_0_FW}               ${MCU_3_0_FW}                                                                   ${C7X_1_FW} ${C7X_2_FW}"
IPC_FW_LIST:j742s2 =  "              ${MCU_1_1_FW} ${MCU_2_0_FW} ${MCU_2_1_FW} ${MCU_3_0_FW} ${MCU_3_1_FW} ${MCU_4_0_FW} ${MCU_4_1_FW}                         ${C7X_1_FW} ${C7X_2_FW} ${C7X_3_FW}"

# Update the am64xx ipc binaries to be consistent with other platforms
do_install:prepend:am64xx() {
        ( cd ${S}/${IPC_FW_DIR}; \
                ln -sf am64-main-r5f0_0-fw ${MCU_1_0_FW}; \
                ln -sf am64-main-r5f0_1-fw ${MCU_1_1_FW}; \
                ln -sf am64-main-r5f1_0-fw ${MCU_2_0_FW}; \
                ln -sf am64-main-r5f1_1-fw ${MCU_2_1_FW}; \
                ln -sf am64-mcu-m4f0_0-fw ${MCU_3_0_FW}; \
        )
}

# Update the am62xx ipc binaries to be consistent with other platforms
do_install:prepend:am62xx() {
        ( cd ${S}/${IPC_FW_DIR}; \
                ln -sf am62-mcu-m4f0_0-fw ${MCU_2_0_FW}; \
        )
}

# Update the am62pxx ipc binaries to be consistent with other platforms
do_install:prepend:am62pxx() {
        ( cd ${S}/${IPC_FW_DIR}; \
                ln -sf am62p-mcu-r5f0_0-fw ${MCU_2_0_FW}; \
        )
}

# Update the am62axx ipc binaries to be consistent with other platforms
do_install:prepend:am62axx() {
        ( cd ${S}/${IPC_FW_DIR}; \
                ln -sf am62a-mcu-r5f0_0-fw ${MCU_2_0_FW}; \
        )
}

# Update the am62dxx ipc binaries to be consistent with other platforms
do_install:prepend:am62dxx() {
        ( cd ${S}/${IPC_FW_DIR}; \
                ln -sf am62a-mcu-r5f0_0-fw ${MCU_2_0_FW}; \
        )
}

do_install() {
    # IPC Firmware
    for FW_NAME in ${IPC_FW_LIST}
    do
        ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh ${S}/${IPC_FW_DIR}/${FW_NAME} ${S}/${IPC_FW_DIR}/${FW_NAME}.signed
    done

    # IPC Firmware
    install -d ${D}${INSTALL_IPC_FW_DIR}
    for FW_NAME in ${IPC_FW_LIST}
    do
        install -m 0644 ${S}/${IPC_FW_DIR}/${FW_NAME}        ${D}${INSTALL_IPC_FW_DIR}
        install -m 0644 ${S}/${IPC_FW_DIR}/${FW_NAME}.signed ${D}${INSTALL_IPC_FW_DIR}
    done
}

# Set up names for the firmwares
ALTERNATIVE:${PN}:am65xx = "\
                    am65x-mcu-r5f0_0-fw   am65x-mcu-r5f0_0-fw-sec \
                    am65x-mcu-r5f0_1-fw   am65x-mcu-r5f0_1-fw-sec \
                    "

ALTERNATIVE:${PN}:am64xx = "\
                    am64-main-r5f0_0-fw   am64-main-r5f0_0-fw-sec \
                    am64-main-r5f0_1-fw   am64-main-r5f0_1-fw-sec \
                    am64-main-r5f1_0-fw   am64-main-r5f1_0-fw-sec \
                    am64-main-r5f1_1-fw   am64-main-r5f1_1-fw-sec \
                    am64-mcu-m4f0_0-fw    am64-mcu-m4f0_0-fw-sec \
                    "

ALTERNATIVE:${PN}:am62xx = "\
                    am62-mcu-m4f0_0-fw    am62-mcu-m4f0_0-fw-sec \
                    "

ALTERNATIVE:${PN}:am62pxx = "\
                    am62p-mcu-r5f0_0-fw   am62p-mcu-r5f0_0-fw-sec \
                    "

ALTERNATIVE:${PN}:am62axx = "\
                    am62a-mcu-r5f0_0-fw   am62a-mcu-r5f0_0-fw-sec \
                    am62a-c71_0-fw        am62a-c71_0-fw-sec \
                    "

ALTERNATIVE:${PN}:am62dxx = "\
                    am62a-mcu-r5f0_0-fw   am62a-mcu-r5f0_0-fw-sec \
                    am62a-c71_0-fw        am62a-c71_0-fw-sec \
                    "

ALTERNATIVE:${PN}:j721e = "\
                    j7-mcu-r5f0_1-fw    j7-mcu-r5f0_1-fw-sec \
                    j7-main-r5f0_0-fw   j7-main-r5f0_0-fw-sec \
                    j7-main-r5f0_1-fw   j7-main-r5f0_1-fw-sec \
                    j7-main-r5f1_0-fw   j7-main-r5f1_0-fw-sec \
                    j7-main-r5f1_1-fw   j7-main-r5f1_1-fw-sec \
                    j7-c66_0-fw         j7-c66_0-fw-sec \
                    j7-c66_1-fw         j7-c66_1-fw-sec \
                    j7-c71_0-fw         j7-c71_0-fw-sec \
                    "

ALTERNATIVE:${PN}:j7200 = "\
                    j7200-mcu-r5f0_1-fw    j7200-mcu-r5f0_1-fw-sec \
                    j7200-main-r5f0_0-fw   j7200-main-r5f0_0-fw-sec \
                    j7200-main-r5f0_1-fw   j7200-main-r5f0_1-fw-sec \
                    "

ALTERNATIVE:${PN}:j721s2 = "\
                    j721s2-mcu-r5f0_1-fw    j721s2-mcu-r5f0_1-fw-sec \
                    j721s2-main-r5f0_0-fw   j721s2-main-r5f0_0-fw-sec \
                    j721s2-main-r5f0_1-fw   j721s2-main-r5f0_1-fw-sec \
                    j721s2-main-r5f1_0-fw   j721s2-main-r5f1_0-fw-sec \
                    j721s2-main-r5f1_1-fw   j721s2-main-r5f1_1-fw-sec \
                    j721s2-c71_0-fw         j721s2-c71_0-fw-sec \
                    j721s2-c71_1-fw         j721s2-c71_1-fw-sec \
                    "

ALTERNATIVE:${PN}:j784s4 = "\
                    j784s4-mcu-r5f0_1-fw    j784s4-mcu-r5f0_1-fw-sec \
                    j784s4-main-r5f0_0-fw   j784s4-main-r5f0_0-fw-sec \
                    j784s4-main-r5f0_1-fw   j784s4-main-r5f0_1-fw-sec \
                    j784s4-main-r5f1_0-fw   j784s4-main-r5f1_0-fw-sec \
                    j784s4-main-r5f1_1-fw   j784s4-main-r5f1_1-fw-sec \
                    j784s4-main-r5f2_0-fw   j784s4-main-r5f2_0-fw-sec \
                    j784s4-main-r5f2_1-fw   j784s4-main-r5f2_1-fw-sec \
                    j784s4-c71_0-fw         j784s4-c71_0-fw-sec \
                    j784s4-c71_1-fw         j784s4-c71_1-fw-sec \
                    j784s4-c71_2-fw         j784s4-c71_2-fw-sec \
                    j784s4-c71_3-fw         j784s4-c71_3-fw-sec \
                    "

ALTERNATIVE:${PN}:j722s = "\
                    j722s-mcu-r5f0_0-fw   j722s-mcu-r5f0_0-fw-sec \
                    j722s-main-r5f0_0-fw  j722s-main-r5f0_0-fw-sec \
                    j722s-c71_0-fw        j722s-c71_0-fw-sec \
                    j722s-c71_1-fw        j722s-c71_1-fw-sec \
                    "

ALTERNATIVE:${PN}:j742s2 = "\
                    j742s2-mcu-r5f0_1-fw    j742s2-mcu-r5f0_1-fw-sec \
                    j742s2-main-r5f0_0-fw   j742s2-main-r5f0_0-fw-sec \
                    j742s2-main-r5f0_1-fw   j742s2-main-r5f0_1-fw-sec \
                    j742s2-main-r5f1_0-fw   j742s2-main-r5f1_0-fw-sec \
                    j742s2-main-r5f1_1-fw   j742s2-main-r5f1_1-fw-sec \
                    j742s2-main-r5f2_0-fw   j742s2-main-r5f2_0-fw-sec \
                    j742s2-main-r5f2_1-fw   j742s2-main-r5f2_1-fw-sec \
                    j742s2-c71_0-fw         j742s2-c71_0-fw-sec \
                    j742s2-c71_1-fw         j742s2-c71_1-fw-sec \
                    j742s2-c71_2-fw         j742s2-c71_2-fw-sec \
                    "

# Set up link names for the firmwares

ALTERNATIVE_LINK_NAME[am65x-mcu-r5f0_0-fw]     = "${nonarch_base_libdir}/firmware/am65x-mcu-r5f0_0-fw"
ALTERNATIVE_LINK_NAME[am65x-mcu-r5f0_0-fw-sec] = "${nonarch_base_libdir}/firmware/am65x-mcu-r5f0_0-fw-sec"
ALTERNATIVE_LINK_NAME[am65x-mcu-r5f0_1-fw]     = "${nonarch_base_libdir}/firmware/am65x-mcu-r5f0_1-fw"
ALTERNATIVE_LINK_NAME[am65x-mcu-r5f0_1-fw-sec] = "${nonarch_base_libdir}/firmware/am65x-mcu-r5f0_1-fw-sec"

ALTERNATIVE_LINK_NAME[am64-main-r5f0_0-fw]     = "${nonarch_base_libdir}/firmware/am64-main-r5f0_0-fw"
ALTERNATIVE_LINK_NAME[am64-main-r5f0_0-fw-sec] = "${nonarch_base_libdir}/firmware/am64-main-r5f0_0-fw-sec"
ALTERNATIVE_LINK_NAME[am64-main-r5f0_1-fw]     = "${nonarch_base_libdir}/firmware/am64-main-r5f0_1-fw"
ALTERNATIVE_LINK_NAME[am64-main-r5f0_1-fw-sec] = "${nonarch_base_libdir}/firmware/am64-main-r5f0_1-fw-sec"
ALTERNATIVE_LINK_NAME[am64-main-r5f1_0-fw]     = "${nonarch_base_libdir}/firmware/am64-main-r5f1_0-fw"
ALTERNATIVE_LINK_NAME[am64-main-r5f1_0-fw-sec] = "${nonarch_base_libdir}/firmware/am64-main-r5f1_0-fw-sec"
ALTERNATIVE_LINK_NAME[am64-main-r5f1_1-fw]     = "${nonarch_base_libdir}/firmware/am64-main-r5f1_1-fw"
ALTERNATIVE_LINK_NAME[am64-main-r5f1_1-fw-sec] = "${nonarch_base_libdir}/firmware/am64-main-r5f1_1-fw-sec"
ALTERNATIVE_LINK_NAME[am64-mcu-m4f0_0-fw]     = "${nonarch_base_libdir}/firmware/am64-mcu-m4f0_0-fw"
ALTERNATIVE_LINK_NAME[am64-mcu-m4f0_0-fw-sec] = "${nonarch_base_libdir}/firmware/am64-mcu-m4f0_0-fw-sec"

ALTERNATIVE_LINK_NAME[am62-mcu-m4f0_0-fw]     = "${nonarch_base_libdir}/firmware/am62-mcu-m4f0_0-fw"
ALTERNATIVE_LINK_NAME[am62-mcu-m4f0_0-fw-sec] = "${nonarch_base_libdir}/firmware/am62-mcu-m4f0_0-fw-sec"

ALTERNATIVE_LINK_NAME[am62p-mcu-r5f0_0-fw]     = "${nonarch_base_libdir}/firmware/am62p-mcu-r5f0_0-fw"
ALTERNATIVE_LINK_NAME[am62p-mcu-r5f0_0-fw-sec] = "${nonarch_base_libdir}/firmware/am62p-mcu-r5f0_0-fw-sec"

ALTERNATIVE_LINK_NAME[am62a-mcu-r5f0_0-fw]     = "${nonarch_base_libdir}/firmware/am62a-mcu-r5f0_0-fw"
ALTERNATIVE_LINK_NAME[am62a-mcu-r5f0_0-fw-sec] = "${nonarch_base_libdir}/firmware/am62a-mcu-r5f0_0-fw-sec"
ALTERNATIVE_LINK_NAME[am62a-c71_0-fw]     = "${nonarch_base_libdir}/firmware/am62a-c71_0-fw"
ALTERNATIVE_LINK_NAME[am62a-c71_0-fw-sec] = "${nonarch_base_libdir}/firmware/am62a-c71_0-fw-sec"

ALTERNATIVE_LINK_NAME[j7-mcu-r5f0_1-fw]     = "${nonarch_base_libdir}/firmware/j7-mcu-r5f0_1-fw"
ALTERNATIVE_LINK_NAME[j7-mcu-r5f0_1-fw-sec] = "${nonarch_base_libdir}/firmware/j7-mcu-r5f0_1-fw-sec"
ALTERNATIVE_LINK_NAME[j7-main-r5f0_0-fw]     = "${nonarch_base_libdir}/firmware/j7-main-r5f0_0-fw"
ALTERNATIVE_LINK_NAME[j7-main-r5f0_0-fw-sec] = "${nonarch_base_libdir}/firmware/j7-main-r5f0_0-fw-sec"
ALTERNATIVE_LINK_NAME[j7-main-r5f0_1-fw]     = "${nonarch_base_libdir}/firmware/j7-main-r5f0_1-fw"
ALTERNATIVE_LINK_NAME[j7-main-r5f0_1-fw-sec] = "${nonarch_base_libdir}/firmware/j7-main-r5f0_1-fw-sec"
ALTERNATIVE_LINK_NAME[j7-main-r5f1_0-fw]     = "${nonarch_base_libdir}/firmware/j7-main-r5f1_0-fw"
ALTERNATIVE_LINK_NAME[j7-main-r5f1_0-fw-sec] = "${nonarch_base_libdir}/firmware/j7-main-r5f1_0-fw-sec"
ALTERNATIVE_LINK_NAME[j7-main-r5f1_1-fw]     = "${nonarch_base_libdir}/firmware/j7-main-r5f1_1-fw"
ALTERNATIVE_LINK_NAME[j7-main-r5f1_1-fw-sec] = "${nonarch_base_libdir}/firmware/j7-main-r5f1_1-fw-sec"
ALTERNATIVE_LINK_NAME[j7-c66_0-fw]     = "${nonarch_base_libdir}/firmware/j7-c66_0-fw"
ALTERNATIVE_LINK_NAME[j7-c66_0-fw-sec] = "${nonarch_base_libdir}/firmware/j7-c66_0-fw-sec"
ALTERNATIVE_LINK_NAME[j7-c66_1-fw]     = "${nonarch_base_libdir}/firmware/j7-c66_1-fw"
ALTERNATIVE_LINK_NAME[j7-c66_1-fw-sec] = "${nonarch_base_libdir}/firmware/j7-c66_1-fw-sec"
ALTERNATIVE_LINK_NAME[j7-c71_0-fw]     = "${nonarch_base_libdir}/firmware/j7-c71_0-fw"
ALTERNATIVE_LINK_NAME[j7-c71_0-fw-sec] = "${nonarch_base_libdir}/firmware/j7-c71_0-fw-sec"

ALTERNATIVE_LINK_NAME[j7200-mcu-r5f0_1-fw]     = "${nonarch_base_libdir}/firmware/j7200-mcu-r5f0_1-fw"
ALTERNATIVE_LINK_NAME[j7200-mcu-r5f0_1-fw-sec] = "${nonarch_base_libdir}/firmware/j7200-mcu-r5f0_1-fw-sec"
ALTERNATIVE_LINK_NAME[j7200-main-r5f0_0-fw]     = "${nonarch_base_libdir}/firmware/j7200-main-r5f0_0-fw"
ALTERNATIVE_LINK_NAME[j7200-main-r5f0_0-fw-sec] = "${nonarch_base_libdir}/firmware/j7200-main-r5f0_0-fw-sec"
ALTERNATIVE_LINK_NAME[j7200-main-r5f0_1-fw]     = "${nonarch_base_libdir}/firmware/j7200-main-r5f0_1-fw"
ALTERNATIVE_LINK_NAME[j7200-main-r5f0_1-fw-sec] = "${nonarch_base_libdir}/firmware/j7200-main-r5f0_1-fw-sec"

ALTERNATIVE_LINK_NAME[j721s2-mcu-r5f0_1-fw]     = "${nonarch_base_libdir}/firmware/j721s2-mcu-r5f0_1-fw"
ALTERNATIVE_LINK_NAME[j721s2-mcu-r5f0_1-fw-sec] = "${nonarch_base_libdir}/firmware/j721s2-mcu-r5f0_1-fw-sec"
ALTERNATIVE_LINK_NAME[j721s2-main-r5f0_0-fw]     = "${nonarch_base_libdir}/firmware/j721s2-main-r5f0_0-fw"
ALTERNATIVE_LINK_NAME[j721s2-main-r5f0_0-fw-sec] = "${nonarch_base_libdir}/firmware/j721s2-main-r5f0_0-fw-sec"
ALTERNATIVE_LINK_NAME[j721s2-main-r5f0_1-fw]     = "${nonarch_base_libdir}/firmware/j721s2-main-r5f0_1-fw"
ALTERNATIVE_LINK_NAME[j721s2-main-r5f0_1-fw-sec] = "${nonarch_base_libdir}/firmware/j721s2-main-r5f0_1-fw-sec"
ALTERNATIVE_LINK_NAME[j721s2-main-r5f1_0-fw]     = "${nonarch_base_libdir}/firmware/j721s2-main-r5f1_0-fw"
ALTERNATIVE_LINK_NAME[j721s2-main-r5f1_0-fw-sec] = "${nonarch_base_libdir}/firmware/j721s2-main-r5f1_0-fw-sec"
ALTERNATIVE_LINK_NAME[j721s2-main-r5f1_1-fw]     = "${nonarch_base_libdir}/firmware/j721s2-main-r5f1_1-fw"
ALTERNATIVE_LINK_NAME[j721s2-main-r5f1_1-fw-sec] = "${nonarch_base_libdir}/firmware/j721s2-main-r5f1_1-fw-sec"
ALTERNATIVE_LINK_NAME[j721s2-c71_0-fw]     = "${nonarch_base_libdir}/firmware/j721s2-c71_0-fw"
ALTERNATIVE_LINK_NAME[j721s2-c71_0-fw-sec] = "${nonarch_base_libdir}/firmware/j721s2-c71_0-fw-sec"
ALTERNATIVE_LINK_NAME[j721s2-c71_1-fw]     = "${nonarch_base_libdir}/firmware/j721s2-c71_1-fw"
ALTERNATIVE_LINK_NAME[j721s2-c71_1-fw-sec] = "${nonarch_base_libdir}/firmware/j721s2-c71_1-fw-sec"

ALTERNATIVE_LINK_NAME[j784s4-mcu-r5f0_1-fw]     = "${nonarch_base_libdir}/firmware/j784s4-mcu-r5f0_1-fw"
ALTERNATIVE_LINK_NAME[j784s4-mcu-r5f0_1-fw-sec] = "${nonarch_base_libdir}/firmware/j784s4-mcu-r5f0_1-fw-sec"
ALTERNATIVE_LINK_NAME[j784s4-main-r5f0_0-fw]     = "${nonarch_base_libdir}/firmware/j784s4-main-r5f0_0-fw"
ALTERNATIVE_LINK_NAME[j784s4-main-r5f0_0-fw-sec] = "${nonarch_base_libdir}/firmware/j784s4-main-r5f0_0-fw-sec"
ALTERNATIVE_LINK_NAME[j784s4-main-r5f0_1-fw]     = "${nonarch_base_libdir}/firmware/j784s4-main-r5f0_1-fw"
ALTERNATIVE_LINK_NAME[j784s4-main-r5f0_1-fw-sec] = "${nonarch_base_libdir}/firmware/j784s4-main-r5f0_1-fw-sec"
ALTERNATIVE_LINK_NAME[j784s4-main-r5f1_0-fw]     = "${nonarch_base_libdir}/firmware/j784s4-main-r5f1_0-fw"
ALTERNATIVE_LINK_NAME[j784s4-main-r5f1_0-fw-sec] = "${nonarch_base_libdir}/firmware/j784s4-main-r5f1_0-fw-sec"
ALTERNATIVE_LINK_NAME[j784s4-main-r5f1_1-fw]     = "${nonarch_base_libdir}/firmware/j784s4-main-r5f1_1-fw"
ALTERNATIVE_LINK_NAME[j784s4-main-r5f1_1-fw-sec] = "${nonarch_base_libdir}/firmware/j784s4-main-r5f1_1-fw-sec"
ALTERNATIVE_LINK_NAME[j784s4-main-r5f2_0-fw]     = "${nonarch_base_libdir}/firmware/j784s4-main-r5f2_0-fw"
ALTERNATIVE_LINK_NAME[j784s4-main-r5f2_0-fw-sec] = "${nonarch_base_libdir}/firmware/j784s4-main-r5f2_0-fw-sec"
ALTERNATIVE_LINK_NAME[j784s4-main-r5f2_1-fw]     = "${nonarch_base_libdir}/firmware/j784s4-main-r5f2_1-fw"
ALTERNATIVE_LINK_NAME[j784s4-main-r5f2_1-fw-sec] = "${nonarch_base_libdir}/firmware/j784s4-main-r5f2_1-fw-sec"
ALTERNATIVE_LINK_NAME[j784s4-c71_0-fw]     = "${nonarch_base_libdir}/firmware/j784s4-c71_0-fw"
ALTERNATIVE_LINK_NAME[j784s4-c71_0-fw-sec] = "${nonarch_base_libdir}/firmware/j784s4-c71_0-fw-sec"
ALTERNATIVE_LINK_NAME[j784s4-c71_1-fw]     = "${nonarch_base_libdir}/firmware/j784s4-c71_1-fw"
ALTERNATIVE_LINK_NAME[j784s4-c71_1-fw-sec] = "${nonarch_base_libdir}/firmware/j784s4-c71_1-fw-sec"
ALTERNATIVE_LINK_NAME[j784s4-c71_2-fw]     = "${nonarch_base_libdir}/firmware/j784s4-c71_2-fw"
ALTERNATIVE_LINK_NAME[j784s4-c71_2-fw-sec] = "${nonarch_base_libdir}/firmware/j784s4-c71_2-fw-sec"
ALTERNATIVE_LINK_NAME[j784s4-c71_3-fw]     = "${nonarch_base_libdir}/firmware/j784s4-c71_3-fw"
ALTERNATIVE_LINK_NAME[j784s4-c71_3-fw-sec] = "${nonarch_base_libdir}/firmware/j784s4-c71_3-fw-sec"

ALTERNATIVE_LINK_NAME[j722s-mcu-r5f0_0-fw]     = "${nonarch_base_libdir}/firmware/j722s-mcu-r5f0_0-fw"
ALTERNATIVE_LINK_NAME[j722s-mcu-r5f0_0-fw-sec] = "${nonarch_base_libdir}/firmware/j722s-mcu-r5f0_0-fw-sec"
ALTERNATIVE_LINK_NAME[j722s-main-r5f0_0-fw]     = "${nonarch_base_libdir}/firmware/j722s-main-r5f0_0-fw"
ALTERNATIVE_LINK_NAME[j722s-main-r5f0_0-fw-sec] = "${nonarch_base_libdir}/firmware/j722s-main-r5f0_0-fw-sec"
ALTERNATIVE_LINK_NAME[j722s-c71_0-fw]     = "${nonarch_base_libdir}/firmware/j722s-c71_0-fw"
ALTERNATIVE_LINK_NAME[j722s-c71_0-fw-sec] = "${nonarch_base_libdir}/firmware/j722s-c71_0-fw-sec"
ALTERNATIVE_LINK_NAME[j722s-c71_1-fw]     = "${nonarch_base_libdir}/firmware/j722s-c71_1-fw"
ALTERNATIVE_LINK_NAME[j722s-c71_1-fw-sec] = "${nonarch_base_libdir}/firmware/j722s-c71_1-fw-sec"

ALTERNATIVE_LINK_NAME[j742s2-mcu-r5f0_1-fw]     = "${nonarch_base_libdir}/firmware/j742s2-mcu-r5f0_1-fw"
ALTERNATIVE_LINK_NAME[j742s2-mcu-r5f0_1-fw-sec] = "${nonarch_base_libdir}/firmware/j742s2-mcu-r5f0_1-fw-sec"
ALTERNATIVE_LINK_NAME[j742s2-main-r5f0_0-fw]     = "${nonarch_base_libdir}/firmware/j742s2-main-r5f0_0-fw"
ALTERNATIVE_LINK_NAME[j742s2-main-r5f0_0-fw-sec] = "${nonarch_base_libdir}/firmware/j742s2-main-r5f0_0-fw-sec"
ALTERNATIVE_LINK_NAME[j742s2-main-r5f0_1-fw]     = "${nonarch_base_libdir}/firmware/j742s2-main-r5f0_1-fw"
ALTERNATIVE_LINK_NAME[j742s2-main-r5f0_1-fw-sec] = "${nonarch_base_libdir}/firmware/j742s2-main-r5f0_1-fw-sec"
ALTERNATIVE_LINK_NAME[j742s2-main-r5f1_0-fw]     = "${nonarch_base_libdir}/firmware/j742s2-main-r5f1_0-fw"
ALTERNATIVE_LINK_NAME[j742s2-main-r5f1_0-fw-sec] = "${nonarch_base_libdir}/firmware/j742s2-main-r5f1_0-fw-sec"
ALTERNATIVE_LINK_NAME[j742s2-main-r5f1_1-fw]     = "${nonarch_base_libdir}/firmware/j742s2-main-r5f1_1-fw"
ALTERNATIVE_LINK_NAME[j742s2-main-r5f1_1-fw-sec] = "${nonarch_base_libdir}/firmware/j742s2-main-r5f1_1-fw-sec"
ALTERNATIVE_LINK_NAME[j742s2-main-r5f2_0-fw]     = "${nonarch_base_libdir}/firmware/j742s2-main-r5f2_0-fw"
ALTERNATIVE_LINK_NAME[j742s2-main-r5f2_0-fw-sec] = "${nonarch_base_libdir}/firmware/j742s2-main-r5f2_0-fw-sec"
ALTERNATIVE_LINK_NAME[j742s2-main-r5f2_1-fw]     = "${nonarch_base_libdir}/firmware/j742s2-main-r5f2_1-fw"
ALTERNATIVE_LINK_NAME[j742s2-main-r5f2_1-fw-sec] = "${nonarch_base_libdir}/firmware/j742s2-main-r5f2_1-fw-sec"
ALTERNATIVE_LINK_NAME[j742s2-c71_0-fw]     = "${nonarch_base_libdir}/firmware/j742s2-c71_0-fw"
ALTERNATIVE_LINK_NAME[j742s2-c71_0-fw-sec] = "${nonarch_base_libdir}/firmware/j742s2-c71_0-fw-sec"
ALTERNATIVE_LINK_NAME[j742s2-c71_1-fw]     = "${nonarch_base_libdir}/firmware/j742s2-c71_1-fw"
ALTERNATIVE_LINK_NAME[j742s2-c71_1-fw-sec] = "${nonarch_base_libdir}/firmware/j742s2-c71_1-fw-sec"
ALTERNATIVE_LINK_NAME[j742s2-c71_2-fw]     = "${nonarch_base_libdir}/firmware/j742s2-c71_2-fw"
ALTERNATIVE_LINK_NAME[j742s2-c71_2-fw-sec] = "${nonarch_base_libdir}/firmware/j742s2-c71_2-fw-sec"

# Create the firmware alternatives

ALTERNATIVE_TARGET[am65x-mcu-r5f0_0-fw]     = "${INSTALL_IPC_FW_DIR}/${MCU_1_0_FW}"
ALTERNATIVE_TARGET[am65x-mcu-r5f0_0-fw-sec] = "${INSTALL_IPC_FW_DIR}/${MCU_1_0_FW}.signed"
ALTERNATIVE_TARGET[am65x-mcu-r5f0_1-fw]     = "${INSTALL_IPC_FW_DIR}/${MCU_1_1_FW}"
ALTERNATIVE_TARGET[am65x-mcu-r5f0_1-fw-sec] = "${INSTALL_IPC_FW_DIR}/${MCU_1_1_FW}.signed"

ALTERNATIVE_TARGET[am64-main-r5f0_0-fw]     = "${INSTALL_IPC_FW_DIR}/${MCU_1_0_FW}"
ALTERNATIVE_TARGET[am64-main-r5f0_0-fw-sec] = "${INSTALL_IPC_FW_DIR}/${MCU_1_0_FW}.signed"
ALTERNATIVE_TARGET[am64-main-r5f0_1-fw]     = "${INSTALL_IPC_FW_DIR}/${MCU_1_1_FW}"
ALTERNATIVE_TARGET[am64-main-r5f0_1-fw-sec] = "${INSTALL_IPC_FW_DIR}/${MCU_1_1_FW}.signed"
ALTERNATIVE_TARGET[am64-main-r5f1_0-fw]     = "${INSTALL_IPC_FW_DIR}/${MCU_2_0_FW}"
ALTERNATIVE_TARGET[am64-main-r5f1_0-fw-sec] = "${INSTALL_IPC_FW_DIR}/${MCU_2_0_FW}.signed"
ALTERNATIVE_TARGET[am64-main-r5f1_1-fw]     = "${INSTALL_IPC_FW_DIR}/${MCU_2_1_FW}"
ALTERNATIVE_TARGET[am64-main-r5f1_1-fw-sec] = "${INSTALL_IPC_FW_DIR}/${MCU_2_1_FW}.signed"
ALTERNATIVE_TARGET[am64-mcu-m4f0_0-fw]     = "${INSTALL_IPC_FW_DIR}/${MCU_3_0_FW}"
ALTERNATIVE_TARGET[am64-mcu-m4f0_0-fw-sec] = "${INSTALL_IPC_FW_DIR}/${MCU_3_0_FW}.signed"

ALTERNATIVE_TARGET[am62-mcu-m4f0_0-fw]     = "${INSTALL_IPC_FW_DIR}/${MCU_2_0_FW}"
ALTERNATIVE_TARGET[am62-mcu-m4f0_0-fw-sec] = "${INSTALL_IPC_FW_DIR}/${MCU_2_0_FW}.signed"

ALTERNATIVE_TARGET[am62p-mcu-r5f0_0-fw]     = "${INSTALL_IPC_FW_DIR}/${MCU_2_0_FW}"
ALTERNATIVE_TARGET[am62p-mcu-r5f0_0-fw-sec] = "${INSTALL_IPC_FW_DIR}/${MCU_2_0_FW}.signed"

ALTERNATIVE_TARGET[am62a-mcu-r5f0_0-fw]     = "${INSTALL_IPC_FW_DIR}/${MCU_2_0_FW}"
ALTERNATIVE_TARGET[am62a-mcu-r5f0_0-fw-sec] = "${INSTALL_IPC_FW_DIR}/${MCU_2_0_FW}.signed"
ALTERNATIVE_TARGET[am62a-c71_0-fw]     = "${INSTALL_IPC_FW_DIR}/${C7X_1_FW}"
ALTERNATIVE_TARGET[am62a-c71_0-fw-sec] = "${INSTALL_IPC_FW_DIR}/${C7X_1_FW}.signed"

ALTERNATIVE_TARGET[j7-mcu-r5f0_1-fw]     = "${INSTALL_IPC_FW_DIR}/${MCU_1_1_FW}"
ALTERNATIVE_TARGET[j7-mcu-r5f0_1-fw-sec] = "${INSTALL_IPC_FW_DIR}/${MCU_1_1_FW}.signed"
ALTERNATIVE_TARGET[j7-main-r5f0_0-fw]     = "${INSTALL_IPC_FW_DIR}/${MCU_2_0_FW}"
ALTERNATIVE_TARGET[j7-main-r5f0_0-fw-sec] = "${INSTALL_IPC_FW_DIR}/${MCU_2_0_FW}.signed"
ALTERNATIVE_TARGET[j7-main-r5f0_1-fw]     = "${INSTALL_IPC_FW_DIR}/${MCU_2_1_FW}"
ALTERNATIVE_TARGET[j7-main-r5f0_1-fw-sec] = "${INSTALL_IPC_FW_DIR}/${MCU_2_1_FW}.signed"
ALTERNATIVE_TARGET[j7-main-r5f1_0-fw]     = "${INSTALL_IPC_FW_DIR}/${MCU_3_0_FW}"
ALTERNATIVE_TARGET[j7-main-r5f1_0-fw-sec] = "${INSTALL_IPC_FW_DIR}/${MCU_3_0_FW}.signed"
ALTERNATIVE_TARGET[j7-main-r5f1_1-fw]     = "${INSTALL_IPC_FW_DIR}/${MCU_3_1_FW}"
ALTERNATIVE_TARGET[j7-main-r5f1_1-fw-sec] = "${INSTALL_IPC_FW_DIR}/${MCU_3_1_FW}.signed"
ALTERNATIVE_TARGET[j7-c66_0-fw]     = "${INSTALL_IPC_FW_DIR}/${C66_1_FW}"
ALTERNATIVE_TARGET[j7-c66_0-fw-sec] = "${INSTALL_IPC_FW_DIR}/${C66_1_FW}.signed"
ALTERNATIVE_TARGET[j7-c66_1-fw]     = "${INSTALL_IPC_FW_DIR}/${C66_2_FW}"
ALTERNATIVE_TARGET[j7-c66_1-fw-sec] = "${INSTALL_IPC_FW_DIR}/${C66_2_FW}.signed"
ALTERNATIVE_TARGET[j7-c71_0-fw]     = "${INSTALL_IPC_FW_DIR}/${C7X_1_FW}"
ALTERNATIVE_TARGET[j7-c71_0-fw-sec] = "${INSTALL_IPC_FW_DIR}/${C7X_1_FW}.signed"

ALTERNATIVE_TARGET[j7200-mcu-r5f0_1-fw]     = "${INSTALL_IPC_FW_DIR}/${MCU_1_1_FW}"
ALTERNATIVE_TARGET[j7200-mcu-r5f0_1-fw-sec] = "${INSTALL_IPC_FW_DIR}/${MCU_1_1_FW}.signed"
ALTERNATIVE_TARGET[j7200-main-r5f0_0-fw]     = "${INSTALL_IPC_FW_DIR}/${MCU_2_0_FW}"
ALTERNATIVE_TARGET[j7200-main-r5f0_0-fw-sec] = "${INSTALL_IPC_FW_DIR}/${MCU_2_0_FW}.signed"
ALTERNATIVE_TARGET[j7200-main-r5f0_1-fw]     = "${INSTALL_IPC_FW_DIR}/${MCU_2_1_FW}"
ALTERNATIVE_TARGET[j7200-main-r5f0_1-fw-sec] = "${INSTALL_IPC_FW_DIR}/${MCU_2_1_FW}.signed"

ALTERNATIVE_TARGET[j721s2-mcu-r5f0_1-fw]     = "${INSTALL_IPC_FW_DIR}/${MCU_1_1_FW}"
ALTERNATIVE_TARGET[j721s2-mcu-r5f0_1-fw-sec] = "${INSTALL_IPC_FW_DIR}/${MCU_1_1_FW}.signed"
ALTERNATIVE_TARGET[j721s2-main-r5f0_0-fw]     = "${INSTALL_IPC_FW_DIR}/${MCU_2_0_FW}"
ALTERNATIVE_TARGET[j721s2-main-r5f0_0-fw-sec] = "${INSTALL_IPC_FW_DIR}/${MCU_2_0_FW}.signed"
ALTERNATIVE_TARGET[j721s2-main-r5f0_1-fw]     = "${INSTALL_IPC_FW_DIR}/${MCU_2_1_FW}"
ALTERNATIVE_TARGET[j721s2-main-r5f0_1-fw-sec] = "${INSTALL_IPC_FW_DIR}/${MCU_2_1_FW}.signed"
ALTERNATIVE_TARGET[j721s2-main-r5f1_0-fw]     = "${INSTALL_IPC_FW_DIR}/${MCU_3_0_FW}"
ALTERNATIVE_TARGET[j721s2-main-r5f1_0-fw-sec] = "${INSTALL_IPC_FW_DIR}/${MCU_3_0_FW}.signed"
ALTERNATIVE_TARGET[j721s2-main-r5f1_1-fw]     = "${INSTALL_IPC_FW_DIR}/${MCU_3_1_FW}"
ALTERNATIVE_TARGET[j721s2-main-r5f1_1-fw-sec] = "${INSTALL_IPC_FW_DIR}/${MCU_3_1_FW}.signed"
ALTERNATIVE_TARGET[j721s2-c71_0-fw]     = "${INSTALL_IPC_FW_DIR}/${C7X_1_FW}"
ALTERNATIVE_TARGET[j721s2-c71_0-fw-sec] = "${INSTALL_IPC_FW_DIR}/${C7X_1_FW}.signed"
ALTERNATIVE_TARGET[j721s2-c71_1-fw]     = "${INSTALL_IPC_FW_DIR}/${C7X_2_FW}"
ALTERNATIVE_TARGET[j721s2-c71_1-fw-sec] = "${INSTALL_IPC_FW_DIR}/${C7X_2_FW}.signed"

ALTERNATIVE_TARGET[j784s4-mcu-r5f0_1-fw]     = "${INSTALL_IPC_FW_DIR}/${MCU_1_1_FW}"
ALTERNATIVE_TARGET[j784s4-mcu-r5f0_1-fw-sec] = "${INSTALL_IPC_FW_DIR}/${MCU_1_1_FW}.signed"
ALTERNATIVE_TARGET[j784s4-main-r5f0_0-fw]     = "${INSTALL_IPC_FW_DIR}/${MCU_2_0_FW}"
ALTERNATIVE_TARGET[j784s4-main-r5f0_0-fw-sec] = "${INSTALL_IPC_FW_DIR}/${MCU_2_0_FW}.signed"
ALTERNATIVE_TARGET[j784s4-main-r5f0_1-fw]     = "${INSTALL_IPC_FW_DIR}/${MCU_2_1_FW}"
ALTERNATIVE_TARGET[j784s4-main-r5f0_1-fw-sec] = "${INSTALL_IPC_FW_DIR}/${MCU_2_1_FW}.signed"
ALTERNATIVE_TARGET[j784s4-main-r5f1_0-fw]     = "${INSTALL_IPC_FW_DIR}/${MCU_3_0_FW}"
ALTERNATIVE_TARGET[j784s4-main-r5f1_0-fw-sec] = "${INSTALL_IPC_FW_DIR}/${MCU_3_0_FW}.signed"
ALTERNATIVE_TARGET[j784s4-main-r5f1_1-fw]     = "${INSTALL_IPC_FW_DIR}/${MCU_3_1_FW}"
ALTERNATIVE_TARGET[j784s4-main-r5f1_1-fw-sec] = "${INSTALL_IPC_FW_DIR}/${MCU_3_1_FW}.signed"
ALTERNATIVE_TARGET[j784s4-main-r5f2_0-fw]     = "${INSTALL_IPC_FW_DIR}/${MCU_4_0_FW}"
ALTERNATIVE_TARGET[j784s4-main-r5f2_0-fw-sec] = "${INSTALL_IPC_FW_DIR}/${MCU_4_0_FW}.signed"
ALTERNATIVE_TARGET[j784s4-main-r5f2_1-fw]     = "${INSTALL_IPC_FW_DIR}/${MCU_4_1_FW}"
ALTERNATIVE_TARGET[j784s4-main-r5f2_1-fw-sec] = "${INSTALL_IPC_FW_DIR}/${MCU_4_1_FW}.signed"
ALTERNATIVE_TARGET[j784s4-c71_0-fw]     = "${INSTALL_IPC_FW_DIR}/${C7X_1_FW}"
ALTERNATIVE_TARGET[j784s4-c71_0-fw-sec] = "${INSTALL_IPC_FW_DIR}/${C7X_1_FW}.signed"
ALTERNATIVE_TARGET[j784s4-c71_1-fw]     = "${INSTALL_IPC_FW_DIR}/${C7X_2_FW}"
ALTERNATIVE_TARGET[j784s4-c71_1-fw-sec] = "${INSTALL_IPC_FW_DIR}/${C7X_2_FW}.signed"
ALTERNATIVE_TARGET[j784s4-c71_2-fw]     = "${INSTALL_IPC_FW_DIR}/${C7X_3_FW}"
ALTERNATIVE_TARGET[j784s4-c71_2-fw-sec] = "${INSTALL_IPC_FW_DIR}/${C7X_3_FW}.signed"
ALTERNATIVE_TARGET[j784s4-c71_3-fw]     = "${INSTALL_IPC_FW_DIR}/${C7X_4_FW}"
ALTERNATIVE_TARGET[j784s4-c71_3-fw-sec] = "${INSTALL_IPC_FW_DIR}/${C7X_4_FW}.signed"

ALTERNATIVE_TARGET[j722s-mcu-r5f0_0-fw]     = "${INSTALL_IPC_FW_DIR}/${MCU_2_0_FW}"
ALTERNATIVE_TARGET[j722s-mcu-r5f0_0-fw-sec] = "${INSTALL_IPC_FW_DIR}/${MCU_2_0_FW}.signed"
ALTERNATIVE_TARGET[j722s-main-r5f0_0-fw]     = "${INSTALL_IPC_FW_DIR}/${MCU_3_0_FW}"
ALTERNATIVE_TARGET[j722s-main-r5f0_0-fw-sec] = "${INSTALL_IPC_FW_DIR}/${MCU_3_0_FW}.signed"
ALTERNATIVE_TARGET[j722s-c71_0-fw]     = "${INSTALL_IPC_FW_DIR}/${C7X_1_FW}"
ALTERNATIVE_TARGET[j722s-c71_0-fw-sec] = "${INSTALL_IPC_FW_DIR}/${C7X_1_FW}.signed"
ALTERNATIVE_TARGET[j722s-c71_1-fw]     = "${INSTALL_IPC_FW_DIR}/${C7X_2_FW}"
ALTERNATIVE_TARGET[j722s-c71_1-fw-sec] = "${INSTALL_IPC_FW_DIR}/${C7X_2_FW}.signed"

ALTERNATIVE_TARGET[j742s2-mcu-r5f0_1-fw]     = "${INSTALL_IPC_FW_DIR}/${MCU_1_1_FW}"
ALTERNATIVE_TARGET[j742s2-mcu-r5f0_1-fw-sec] = "${INSTALL_IPC_FW_DIR}/${MCU_1_1_FW}.signed"
ALTERNATIVE_TARGET[j742s2-main-r5f0_0-fw]     = "${INSTALL_IPC_FW_DIR}/${MCU_2_0_FW}"
ALTERNATIVE_TARGET[j742s2-main-r5f0_0-fw-sec] = "${INSTALL_IPC_FW_DIR}/${MCU_2_0_FW}.signed"
ALTERNATIVE_TARGET[j742s2-main-r5f0_1-fw]     = "${INSTALL_IPC_FW_DIR}/${MCU_2_1_FW}"
ALTERNATIVE_TARGET[j742s2-main-r5f0_1-fw-sec] = "${INSTALL_IPC_FW_DIR}/${MCU_2_1_FW}.signed"
ALTERNATIVE_TARGET[j742s2-main-r5f1_0-fw]     = "${INSTALL_IPC_FW_DIR}/${MCU_3_0_FW}"
ALTERNATIVE_TARGET[j742s2-main-r5f1_0-fw-sec] = "${INSTALL_IPC_FW_DIR}/${MCU_3_0_FW}.signed"
ALTERNATIVE_TARGET[j742s2-main-r5f1_1-fw]     = "${INSTALL_IPC_FW_DIR}/${MCU_3_1_FW}"
ALTERNATIVE_TARGET[j742s2-main-r5f1_1-fw-sec] = "${INSTALL_IPC_FW_DIR}/${MCU_3_1_FW}.signed"
ALTERNATIVE_TARGET[j742s2-main-r5f2_0-fw]     = "${INSTALL_IPC_FW_DIR}/${MCU_4_0_FW}"
ALTERNATIVE_TARGET[j742s2-main-r5f2_0-fw-sec] = "${INSTALL_IPC_FW_DIR}/${MCU_4_0_FW}.signed"
ALTERNATIVE_TARGET[j742s2-main-r5f2_1-fw]     = "${INSTALL_IPC_FW_DIR}/${MCU_4_1_FW}"
ALTERNATIVE_TARGET[j742s2-main-r5f2_1-fw-sec] = "${INSTALL_IPC_FW_DIR}/${MCU_4_1_FW}.signed"
ALTERNATIVE_TARGET[j742s2-c71_0-fw]     = "${INSTALL_IPC_FW_DIR}/${C7X_1_FW}"
ALTERNATIVE_TARGET[j742s2-c71_0-fw-sec] = "${INSTALL_IPC_FW_DIR}/${C7X_1_FW}.signed"
ALTERNATIVE_TARGET[j742s2-c71_1-fw]     = "${INSTALL_IPC_FW_DIR}/${C7X_2_FW}"
ALTERNATIVE_TARGET[j742s2-c71_1-fw-sec] = "${INSTALL_IPC_FW_DIR}/${C7X_2_FW}.signed"
ALTERNATIVE_TARGET[j742s2-c71_2-fw]     = "${INSTALL_IPC_FW_DIR}/${C7X_3_FW}"
ALTERNATIVE_TARGET[j742s2-c71_2-fw-sec] = "${INSTALL_IPC_FW_DIR}/${C7X_3_FW}.signed"

ALTERNATIVE_PRIORITY = "10"
