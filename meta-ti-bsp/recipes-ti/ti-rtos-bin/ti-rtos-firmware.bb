SUMMARY = "TI RTOS prebuilt binary firmware images"

LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://${COREBASE}/../meta-ti/licenses/TI-TFL;md5=a1b59cb7ba626b9dbbcbf00f3fbc438a"

COMPATIBLE_MACHINE = "k3"

PACKAGE_ARCH = "${MACHINE_ARCH}"

INHIBIT_DEFAULT_DEPS = "1"

inherit deploy
inherit update-alternatives

PLAT_SFX = ""
PLAT_SFX:j721e = "j721e"
PLAT_SFX:j7200 = "j7200"
PLAT_SFX:j721s2 = "j721s2"
PLAT_SFX:j784s4 = "j784s4"
PLAT_SFX:am65xx = "am65xx"
PLAT_SFX:am64xx = "am64xx"
PLAT_SFX:am62xx = "am62xx"

FILESEXTRAPATHS:prepend := "${METATIBASE}/recipes-bsp/ti-sci-fw/files/:"
require recipes-bsp/ti-linux-fw/ti-linux-fw.inc

CORESDK_RTOS_VERSION ?= "08.00.00.26"
PV = "${CORESDK_RTOS_VERSION}"

CLEANBROKEN = "1"
PR = "${INC_PR}.0"

# Secure Build
inherit ti-secdev

RTOS_ETH_FW_DIR = "${S}/ti-eth/${PLAT_SFX}"
RTOS_DM_FW_DIR = "${S}/ti-dm/${PLAT_SFX}"
RTOS_IPC_FW_DIR = "${S}/ti-ipc/${PLAT_SFX}"

# For back-ward compatability keeping legacy firmware folder name
# TODO: fix this in next version
LEGACY_ETH_FW_DIR = "${D}${nonarch_base_libdir}/firmware/ethfw/"
LEGACY_IPC_FW_DIR = "${D}${nonarch_base_libdir}/firmware/pdk-ipc/"
LEGACY_DM_FW_DIR  = "${D}${nonarch_base_libdir}/firmware/pdk-ipc/"

DM_FIRMWARE = "ipc_echo_testb_mcu1_0_release_strip.xer5f"

MCU_1_0_FW = "ipc_echo_test_mcu1_0_release_strip.xer5f"
MCU_1_1_FW = "ipc_echo_test_mcu1_1_release_strip.xer5f"
MCU_2_0_FW = "ipc_echo_test_mcu2_0_release_strip.xer5f"
MCU_2_1_FW = "ipc_echo_test_mcu2_1_release_strip.xer5f"
MCU_3_0_FW = "ipc_echo_test_mcu3_0_release_strip.xer5f"
MCU_3_1_FW = "ipc_echo_test_mcu3_1_release_strip.xer5f"
MCU_4_0_FW = "ipc_echo_test_mcu4_0_release_strip.xer5f"
MCU_4_1_FW = "ipc_echo_test_mcu4_1_release_strip.xer5f"
C66_1_FW   = "ipc_echo_test_c66xdsp_1_release_strip.xe66"
C66_2_FW   = "ipc_echo_test_c66xdsp_2_release_strip.xe66"
C7X_1_FW   = "ipc_echo_test_c7x_1_release_strip.xe71"
C7X_2_FW   = "ipc_echo_test_c7x_2_release_strip.xe71"
C7X_3_FW   = "ipc_echo_test_c7x_3_release_strip.xe71"
C7X_4_FW   = "ipc_echo_test_c7x_4_release_strip.xe71"

ETH_FW = "app_remoteswitchcfg_server_strip.xer5f"

DM_FW_LIST = ""
DM_FW_LIST:am65xx = ""
DM_FW_LIST:j721e =  "${DM_FIRMWARE}"
DM_FW_LIST:j7200 =  "${DM_FIRMWARE}"
DM_FW_LIST:j721s2 = "${DM_FIRMWARE}"
DM_FW_LIST:am64xx = ""
DM_FW_LIST:am62xx = "${DM_FIRMWARE}"
DM_FW_LIST:j784s4 = "${DM_FIRMWARE}"

IPC_FW_LIST = ""
IPC_FW_LIST:am65xx = "${MCU_1_0_FW} ${MCU_1_1_FW}"
IPC_FW_LIST:j721e =  "                            ${MCU_2_0_FW} ${MCU_2_1_FW} ${MCU_3_0_FW} ${MCU_3_1_FW} ${C66_1_FW} ${C66_2_FW} ${C7X_1_FW}"
IPC_FW_LIST:j7200 =  "              ${MCU_1_1_FW} ${MCU_2_0_FW} ${MCU_2_1_FW}"
IPC_FW_LIST:j721s2 = "                            ${MCU_2_0_FW} ${MCU_2_1_FW} ${MCU_2_0_FW} ${MCU_3_1_FW}                         ${C7X_1_FW} ${C7X_2_FW}"
IPC_FW_LIST:am64xx = "${MCU_1_0_FW} ${MCU_1_1_FW} ${MCU_2_0_FW} ${MCU_2_1_FW} ${MCU_3_0_FW}"
IPC_FW_LIST:am62xx = "                            ${MCU_2_0_FW}"
IPC_FW_LIST:j784s4 = "              ${MCU_1_1_FW} ${MCU_2_0_FW} ${MCU_2_1_FW} ${MCU_3_0_FW} ${MCU_3_1_FW}                         ${C7X_1_FW} ${C7X_2_FW} ${C7X_3_FW} ${C7X_4_FW}"

ETH_FW_LIST = ""
ETH_FW_LIST:am65xx = ""
ETH_FW_LIST:j721e =  "${ETH_FW}"
ETH_FW_LIST:j7200 =  "${ETH_FW}"
ETH_FW_LIST:j721s2 = ""
ETH_FW_LIST:am64xx = ""
ETH_FW_LIST:am62xx = ""
ETH_FW_LIST:j784s4 = "${ETH_FW}"

# Update the am64xx ipc binaries to be consistent with other platforms
do_install:prepend:am64xx() {
        ( cd ${RTOS_IPC_FW_DIR}; \
                ln -s am64-main-r5f0_0-fw ${MCU_1_0_FW}; \
                ln -s am64-main-r5f0_1-fw ${MCU_1_1_FW}; \
                ln -s am64-main-r5f1_0-fw ${MCU_2_0_FW}; \
                ln -s am64-main-r5f1_1-fw ${MCU_2_1_FW}; \
                ln -s am64-mcu-m4f0_0-fw ${MCU_3_0_FW}; \
        )
}

# Update the am62xx ipc binaries to be consistent with other platforms
do_install:prepend:am62xx() {
        ( cd ${RTOS_IPC_FW_DIR}; \
                ln -s am62-mcu-m4f0_0-fw ${MCU_2_0_FW}; \
        )
}

# Sign the firmware
do_install:prepend() {
    # DM Firmware
    for FW_NAME in ${DM_FW_LIST}
    do
      ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh ${RTOS_DM_FW_DIR}/${FW_NAME} ${RTOS_DM_FW_DIR}/${FW_NAME}.signed
    done

    # IPC Firmware
    for FW_NAME in ${IPC_FW_LIST}
    do
      ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh ${RTOS_IPC_FW_DIR}/${FW_NAME} ${RTOS_IPC_FW_DIR}/${FW_NAME}.signed
    done

    # ETH firmware
    for FW_NAME in ${ETH_FW_LIST}
    do
        ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh ${RTOS_ETH_FW_DIR}/${FW_NAME} ${RTOS_ETH_FW_DIR}/${FW_NAME}.signed;
    done
}

#Install all R5 & DSP ipc echo test binaries in lib/firmware/pdk-ipc, with softlinks up a level
do_install() {
    # DM Firmware
    install -d ${LEGACY_DM_FW_DIR}
    for FW_NAME in ${DM_FW_LIST}
    do
      install -m 0644 ${RTOS_DM_FW_DIR}/${FW_NAME}        ${LEGACY_DM_FW_DIR}/${FW_NAME}.unsigned
      install -m 0644 ${RTOS_DM_FW_DIR}/${FW_NAME}.signed ${LEGACY_DM_FW_DIR}/${FW_NAME}
    done

    # IPC Firmware
    install -d ${LEGACY_IPC_FW_DIR}
    for FW_NAME in ${IPC_FW_LIST}
    do
      install -m 0644 ${RTOS_IPC_FW_DIR}/${FW_NAME}        ${LEGACY_IPC_FW_DIR}
      install -m 0644 ${RTOS_IPC_FW_DIR}/${FW_NAME}.signed ${LEGACY_IPC_FW_DIR}
    done

    # ETH firmware
    install -d ${LEGACY_ETH_FW_DIR}
    for FW_NAME in ${ETH_FW_LIST}
    do
      install -m 0644 ${RTOS_ETH_FW_DIR}/${FW_NAME}        ${LEGACY_ETH_FW_DIR}
      install -m 0644 ${RTOS_ETH_FW_DIR}/${FW_NAME}.signed ${LEGACY_ETH_FW_DIR}
    done
}

do_deploy() {
    # DM Firmware is needed for rebuilding U-Boot
    install -d ${DEPLOYDIR}
    for FW_NAME in ${DM_FW_LIST}
    do
      install -m 0644 ${RTOS_DM_FW_DIR}/${FW_NAME}        ${DEPLOYDIR}/${FW_NAME}.unsigned
      install -m 0644 ${RTOS_DM_FW_DIR}/${FW_NAME}.signed ${DEPLOYDIR}/${FW_NAME}
    done
}

# Set up names for the firmwares
ALTERNATIVE:${PN}:am65xx = "\
                    am65x-mcu-r5f0_0-fw \
                    am65x-mcu-r5f0_1-fw \
                    "

ALTERNATIVE:${PN}:am64xx = "\
                    am64-main-r5f0_0-fw \
                    am64-main-r5f0_1-fw \
                    am64-main-r5f1_0-fw \
                    am64-main-r5f1_1-fw \
                    am64-mcu-m4f0_0-fw \
                    "
ALTERNATIVE:${PN}:am62xx = "\
                    am62-mcu-m4f0_0-fw \
                    am62-main-r5f0_0-fw \
                    "

ALTERNATIVE:${PN}:j721e-hs-evm = "\
                    j7-mcu-r5f0_0-fw \
                    j7-mcu-r5f0_1-fw \
                    j7-main-r5f0_0-fw \
                    j7-main-r5f0_1-fw \
                    j7-main-r5f1_0-fw \
                    j7-main-r5f1_1-fw \
                    j7-c66_0-fw \
                    j7-c66_1-fw \
                    j7-c71_0-fw\
                    j7-main-r5f0_0-fw-sec \
                    j7-main-r5f0_1-fw-sec \
                    j7-main-r5f1_0-fw-sec \
                    j7-main-r5f1_1-fw-sec \
                    j7-c66_0-fw-sec \
                    j7-c66_1-fw-sec \
                    j7-c71_0-fw-sec \
                    "

ALTERNATIVE:${PN}:j721e-evm = "\
                    j7-mcu-r5f0_0-fw \
                    j7-mcu-r5f0_1-fw \
                    j7-main-r5f0_0-fw \
                    j7-main-r5f0_1-fw \
                    j7-main-r5f1_0-fw \
                    j7-main-r5f1_1-fw \
                    j7-c66_0-fw \
                    j7-c66_1-fw \
                    j7-c71_0-fw\
                    "

ALTERNATIVE:${PN}:j7200-evm = "\
                    j7200-mcu-r5f0_0-fw \
                    j7200-mcu-r5f0_1-fw \
                    j7200-main-r5f0_0-fw \
                    j7200-main-r5f0_1-fw \
                    "

ALTERNATIVE:${PN}:j7200-hs-evm = "\
                    j7200-mcu-r5f0_0-fw \
                    j7200-mcu-r5f0_1-fw \
                    j7200-main-r5f0_0-fw \
                    j7200-main-r5f0_1-fw \
                    j7200-main-r5f0_0-fw-sec \
                    j7200-main-r5f0_1-fw-sec \
                    "

ALTERNATIVE_${PN}:j721s2-evm = "\
                    j721s2-mcu-r5f0_0-fw \
                    j721s2-mcu-r5f0_1-fw \
                    j721s2-main-r5f0_0-fw \
                    j721s2-main-r5f0_1-fw \
                    j721s2-main-r5f1_0-fw \
                    j721s2-main-r5f1_1-fw \
                    j721s2-c71_0-fw \
                    j721s2-c71_1-fw \
                    "

ALTERNATIVE:${PN}:j721s2-hs-evm = "\
                    j721s2-mcu-r5f0_0-fw \
                    j721s2-mcu-r5f0_1-fw \
                    j721s2-main-r5f0_0-fw \
                    j721s2-main-r5f0_1-fw \
                    j721s2-main-r5f1_0-fw \
                    j721s2-main-r5f1_1-fw \
                    j721s2-c71_0-fw \
                    j721s2-c71_1-fw \
                    j721s2-main-r5f0_0-fw-sec \
                    j721s2-main-r5f0_1-fw-sec \
                    j721s2-main-r5f1_0-fw-sec \
                    j721s2-main-r5f1_1-fw-sec \
                    j721s2-c71_0-fw-sec \
                    j721s2-c71_1-fw-sec \
                    "

ALTERNATIVE:${PN}:j784s4-evm = "\
                    j784s4-mcu-r5f0_0-fw \
                    j784s4-mcu-r5f0_1-fw \
                    j784s4-main-r5f0_0-fw \
                    j784s4-main-r5f0_1-fw \
                    j784s4-main-r5f1_0-fw \
                    j784s4-main-r5f1_1-fw \
                    j784s4-main-r5f2_0-fw \
                    j784s4-main-r5f2_1-fw \
                    j784s4-c71_0-fw \
                    j784s4-c71_1-fw \
                    j784s4-c71_2-fw \
                    j784s4-c71_3-fw \
                    "

# Set up link names for the firmwares

ALTERNATIVE_LINK_NAME[am65x-mcu-r5f0_0-fw] = "${nonarch_base_libdir}/firmware/am65x-mcu-r5f0_0-fw"
ALTERNATIVE_LINK_NAME[am65x-mcu-r5f0_1-fw] = "${nonarch_base_libdir}/firmware/am65x-mcu-r5f0_1-fw"

ALTERNATIVE_LINK_NAME[am64-main-r5f0_0-fw] = "${nonarch_base_libdir}/firmware/am64-main-r5f0_0-fw"
ALTERNATIVE_LINK_NAME[am64-main-r5f0_1-fw] = "${nonarch_base_libdir}/firmware/am64-main-r5f0_1-fw"
ALTERNATIVE_LINK_NAME[am64-main-r5f1_0-fw] = "${nonarch_base_libdir}/firmware/am64-main-r5f1_0-fw"
ALTERNATIVE_LINK_NAME[am64-main-r5f1_1-fw] = "${nonarch_base_libdir}/firmware/am64-main-r5f1_1-fw"
ALTERNATIVE_LINK_NAME[am64-mcu-m4f0_0-fw] = "${nonarch_base_libdir}/firmware/am64-mcu-m4f0_0-fw"

ALTERNATIVE_LINK_NAME[am62-main-r5f0_0-fw] = "${nonarch_base_libdir}/firmware/am62-main-r5f0_0-fw"
ALTERNATIVE_LINK_NAME[am62-mcu-m4f0_0-fw] = "${nonarch_base_libdir}/firmware/am62-mcu-m4f0_0-fw"

ALTERNATIVE_LINK_NAME[j7-mcu-r5f0_0-fw] = "${nonarch_base_libdir}/firmware/j7-mcu-r5f0_0-fw"
ALTERNATIVE_LINK_NAME[j7-mcu-r5f0_1-fw] = "${nonarch_base_libdir}/firmware/j7-mcu-r5f0_1-fw"
ALTERNATIVE_LINK_NAME[j7-main-r5f0_0-fw] = "${nonarch_base_libdir}/firmware/j7-main-r5f0_0-fw"
ALTERNATIVE_LINK_NAME[j7-main-r5f0_1-fw] = "${nonarch_base_libdir}/firmware/j7-main-r5f0_1-fw"
ALTERNATIVE_LINK_NAME[j7-main-r5f1_0-fw] = "${nonarch_base_libdir}/firmware/j7-main-r5f1_0-fw"
ALTERNATIVE_LINK_NAME[j7-main-r5f1_1-fw] = "${nonarch_base_libdir}/firmware/j7-main-r5f1_1-fw"
ALTERNATIVE_LINK_NAME[j7-c66_0-fw] = "${nonarch_base_libdir}/firmware/j7-c66_0-fw"
ALTERNATIVE_LINK_NAME[j7-c66_1-fw] = "${nonarch_base_libdir}/firmware/j7-c66_1-fw"
ALTERNATIVE_LINK_NAME[j7-c71_0-fw] = "${nonarch_base_libdir}/firmware/j7-c71_0-fw"

ALTERNATIVE_LINK_NAME[j7-main-r5f0_0-fw-sec] = "${base_libdir}/firmware/j7-main-r5f0_0-fw-sec"
ALTERNATIVE_LINK_NAME[j7-main-r5f0_1-fw-sec] = "${base_libdir}/firmware/j7-main-r5f0_1-fw-sec"
ALTERNATIVE_LINK_NAME[j7-main-r5f1_0-fw-sec] = "${base_libdir}/firmware/j7-main-r5f1_0-fw-sec"
ALTERNATIVE_LINK_NAME[j7-main-r5f1_1-fw-sec] = "${base_libdir}/firmware/j7-main-r5f1_1-fw-sec"
ALTERNATIVE_LINK_NAME[j7-c66_0-fw-sec] = "${base_libdir}/firmware/j7-c66_0-fw-sec"
ALTERNATIVE_LINK_NAME[j7-c66_1-fw-sec] = "${base_libdir}/firmware/j7-c66_1-fw-sec"
ALTERNATIVE_LINK_NAME[j7-c71_0-fw-sec] = "${base_libdir}/firmware/j7-c71_0-fw-sec"

ALTERNATIVE_LINK_NAME[j7200-mcu-r5f0_0-fw] = "${nonarch_base_libdir}/firmware/j7200-mcu-r5f0_0-fw"
ALTERNATIVE_LINK_NAME[j7200-mcu-r5f0_1-fw] = "${nonarch_base_libdir}/firmware/j7200-mcu-r5f0_1-fw"
ALTERNATIVE_LINK_NAME[j7200-main-r5f0_0-fw] = "${nonarch_base_libdir}/firmware/j7200-main-r5f0_0-fw"
ALTERNATIVE_LINK_NAME[j7200-main-r5f0_1-fw] = "${nonarch_base_libdir}/firmware/j7200-main-r5f0_1-fw"

ALTERNATIVE_LINK_NAME[j7200-main-r5f0_0-fw-sec] = "${base_libdir}/firmware/j7200-main-r5f0_0-fw-sec"
ALTERNATIVE_LINK_NAME[j7200-main-r5f0_1-fw-sec] = "${base_libdir}/firmware/j7200-main-r5f0_1-fw-sec"

ALTERNATIVE_LINK_NAME[j721s2-mcu-r5f0_0-fw] = "${nonarch_base_libdir}/firmware/j721s2-mcu-r5f0_0-fw"
ALTERNATIVE_LINK_NAME[j721s2-mcu-r5f0_1-fw] = "${nonarch_base_libdir}/firmware/j721s2-mcu-r5f0_1-fw"
ALTERNATIVE_LINK_NAME[j721s2-main-r5f0_0-fw] = "${nonarch_base_libdir}/firmware/j721s2-main-r5f0_0-fw"
ALTERNATIVE_LINK_NAME[j721s2-main-r5f0_1-fw] = "${nonarch_base_libdir}/firmware/j721s2-main-r5f0_1-fw"
ALTERNATIVE_LINK_NAME[j721s2-main-r5f1_0-fw] = "${nonarch_base_libdir}/firmware/j721s2-main-r5f1_0-fw"
ALTERNATIVE_LINK_NAME[j721s2-main-r5f1_1-fw] = "${nonarch_base_libdir}/firmware/j721s2-main-r5f1_1-fw"
ALTERNATIVE_LINK_NAME[j721s2-c71_0-fw] = "${nonarch_base_libdir}/firmware/j721s2-c71_0-fw"
ALTERNATIVE_LINK_NAME[j721s2-c71_1-fw] = "${nonarch_base_libdir}/firmware/j721s2-c71_1-fw"

ALTERNATIVE_LINK_NAME[j721s2-main-r5f0_0-fw-sec] = "${base_libdir}/firmware/j721s2-main-r5f0_0-fw-sec"
ALTERNATIVE_LINK_NAME[j721s2-main-r5f0_1-fw-sec] = "${base_libdir}/firmware/j721s2-main-r5f0_1-fw-sec"
ALTERNATIVE_LINK_NAME[j721s2-main-r5f1_0-fw-sec] = "${base_libdir}/firmware/j721s2-main-r5f1_0-fw-sec"
ALTERNATIVE_LINK_NAME[j721s2-main-r5f1_1-fw-sec] = "${base_libdir}/firmware/j721s2-main-r5f1_1-fw-sec"
ALTERNATIVE_LINK_NAME[j721s2-c71_0-fw-sec] = "${base_libdir}/firmware/j721s2-c71_0-fw-sec"
ALTERNATIVE_LINK_NAME[j721s2-c71_1-fw-sec] = "${base_libdir}/firmware/j721s2-c71_1-fw-sec"

ALTERNATIVE_LINK_NAME[j784s4-mcu-r5f0_0-fw] = "${base_libdir}/firmware/j784s4-mcu-r5f0_0-fw"
ALTERNATIVE_LINK_NAME[j784s4-mcu-r5f0_1-fw] = "${base_libdir}/firmware/j784s4-mcu-r5f0_1-fw"
ALTERNATIVE_LINK_NAME[j784s4-main-r5f0_0-fw] = "${base_libdir}/firmware/j784s4-main-r5f0_0-fw"
ALTERNATIVE_LINK_NAME[j784s4-main-r5f0_1-fw] = "${base_libdir}/firmware/j784s4-main-r5f0_1-fw"
ALTERNATIVE_LINK_NAME[j784s4-main-r5f1_0-fw] = "${base_libdir}/firmware/j784s4-main-r5f1_0-fw"
ALTERNATIVE_LINK_NAME[j784s4-main-r5f1_1-fw] = "${base_libdir}/firmware/j784s4-main-r5f1_1-fw"
ALTERNATIVE_LINK_NAME[j784s4-main-r5f2_0-fw] = "${base_libdir}/firmware/j784s4-main-r5f2_0-fw"
ALTERNATIVE_LINK_NAME[j784s4-main-r5f2_1-fw] = "${base_libdir}/firmware/j784s4-main-r5f2_1-fw"
ALTERNATIVE_LINK_NAME[j784s4-c71_0-fw] = "${base_libdir}/firmware/j784s4-c71_0-fw"
ALTERNATIVE_LINK_NAME[j784s4-c71_1-fw] = "${base_libdir}/firmware/j784s4-c71_1-fw"
ALTERNATIVE_LINK_NAME[j784s4-c71_2-fw] = "${base_libdir}/firmware/j784s4-c71_2-fw"
ALTERNATIVE_LINK_NAME[j784s4-c71_3-fw] = "${base_libdir}/firmware/j784s4-c71_3-fw"

# Create the firmware alternatives

ALTERNATIVE_TARGET[am65x-mcu-r5f0_0-fw] = "${nonarch_base_libdir}/firmware/pdk-ipc/${MCU_1_0_FW}"
ALTERNATIVE_TARGET[am65x-mcu-r5f0_1-fw] = "${nonarch_base_libdir}/firmware/pdk-ipc/${MCU_1_1_FW}"

ALTERNATIVE_TARGET[am64-main-r5f0_0-fw] = "${nonarch_base_libdir}/firmware/pdk-ipc/${MCU_1_0_FW}"
ALTERNATIVE_TARGET[am64-main-r5f0_1-fw] = "${nonarch_base_libdir}/firmware/pdk-ipc/${MCU_1_1_FW}"
ALTERNATIVE_TARGET[am64-main-r5f1_0-fw] = "${nonarch_base_libdir}/firmware/pdk-ipc/${MCU_2_0_FW}"
ALTERNATIVE_TARGET[am64-main-r5f1_1-fw] = "${nonarch_base_libdir}/firmware/pdk-ipc/${MCU_2_1_FW}"
ALTERNATIVE_TARGET[am64-mcu-m4f0_0-fw] = "${nonarch_base_libdir}/firmware/pdk-ipc/${MCU_3_0_FW}"

ALTERNATIVE_TARGET[am62-main-r5f0_0-fw] = "${nonarch_base_libdir}/firmware/pdk-ipc/${DM_FIRMWARE}"
ALTERNATIVE_TARGET[am62-mcu-m4f0_0-fw] = "${nonarch_base_libdir}/firmware/pdk-ipc/${MCU_2_0_FW}"

ALTERNATIVE_TARGET[j7-mcu-r5f0_0-fw] = "${nonarch_base_libdir}/firmware/pdk-ipc/${DM_FIRMWARE}"
ALTERNATIVE_TARGET[j7-mcu-r5f0_1-fw] = "${nonarch_base_libdir}/firmware/pdk-ipc/${MCU_1_1_FW}"
ALTERNATIVE_TARGET[j7-main-r5f0_0-fw] = "${nonarch_base_libdir}/firmware/ethfw/${ETH_FW}"
ALTERNATIVE_TARGET[j7-main-r5f0_1-fw] = "${nonarch_base_libdir}/firmware/pdk-ipc/${MCU_2_1_FW}"
ALTERNATIVE_TARGET[j7-main-r5f1_0-fw] = "${nonarch_base_libdir}/firmware/pdk-ipc/${MCU_3_0_FW}"
ALTERNATIVE_TARGET[j7-main-r5f1_1-fw] = "${nonarch_base_libdir}/firmware/pdk-ipc/${MCU_3_1_FW}"
ALTERNATIVE_TARGET[j7-c66_0-fw] = "${nonarch_base_libdir}/firmware/pdk-ipc/${C66_1_FW}"
ALTERNATIVE_TARGET[j7-c66_1-fw] = "${nonarch_base_libdir}/firmware/pdk-ipc/${C66_2_FW}"
ALTERNATIVE_TARGET[j7-c71_0-fw] = "${nonarch_base_libdir}/firmware/pdk-ipc/${C7X_1_FW}"

ALTERNATIVE_TARGET[j7-main-r5f0_0-fw-sec] = "${base_libdir}/firmware/ethfw/${ETH_FW}.signed"
ALTERNATIVE_TARGET[j7-main-r5f0_1-fw-sec] = "${base_libdir}/firmware/pdk-ipc/${MCU_2_1_FW}.signed"
ALTERNATIVE_TARGET[j7-main-r5f1_0-fw-sec] = "${base_libdir}/firmware/pdk-ipc/${MCU_3_0_FW}.signed"
ALTERNATIVE_TARGET[j7-main-r5f1_1-fw-sec] = "${base_libdir}/firmware/pdk-ipc/${MCU_3_1_FW}.signed"
ALTERNATIVE_TARGET[j7-c66_0-fw-sec] = "${base_libdir}/firmware/pdk-ipc/${C66_1_FW}.signed"
ALTERNATIVE_TARGET[j7-c66_1-fw-sec] = "${base_libdir}/firmware/pdk-ipc/${C66_2_FW}.signed"
ALTERNATIVE_TARGET[j7-c71_0-fw-sec] = "${base_libdir}/firmware/pdk-ipc/${C7X_1_FW}.signed"

ALTERNATIVE_TARGET[j7200-mcu-r5f0_0-fw] = "${nonarch_base_libdir}/firmware/pdk-ipc/${DM_FIRMWARE}"
ALTERNATIVE_TARGET[j7200-mcu-r5f0_1-fw] = "${nonarch_base_libdir}/firmware/pdk-ipc/${MCU_1_1_FW}"
ALTERNATIVE_TARGET[j7200-main-r5f0_0-fw] = "${nonarch_base_libdir}/firmware/ethfw/${ETH_FW}"
ALTERNATIVE_TARGET[j7200-main-r5f0_1-fw] = "${nonarch_base_libdir}/firmware/pdk-ipc/${MCU_2_1_FW}"

ALTERNATIVE_TARGET[j7200-main-r5f0_0-fw-sec] = "${base_libdir}/firmware/ethfw/${ETH_FW}.signed"
ALTERNATIVE_TARGET[j7200-main-r5f0_1-fw-sec] = "${base_libdir}/firmware/pdk-ipc/${MCU_2_1_FW}.signed"

ALTERNATIVE_TARGET[j721s2-mcu-r5f0_0-fw] = "${nonarch_base_libdir}/firmware/pdk-ipc/${DM_FIRMWARE}"
ALTERNATIVE_TARGET[j721s2-mcu-r5f0_1-fw] = "${nonarch_base_libdir}/firmware/pdk-ipc/${MCU_1_1_FW}"
ALTERNATIVE_TARGET[j721s2-main-r5f0_0-fw] = "${nonarch_base_libdir}/firmware/pdk-ipc/${MCU_2_0_FW}"
ALTERNATIVE_TARGET[j721s2-main-r5f0_1-fw] = "${nonarch_base_libdir}/firmware/pdk-ipc/${MCU_2_1_FW}"
ALTERNATIVE_TARGET[j721s2-main-r5f1_0-fw] = "${nonarch_base_libdir}/firmware/pdk-ipc/${MCU_3_0_FW}"
ALTERNATIVE_TARGET[j721s2-main-r5f1_1-fw] = "${nonarch_base_libdir}/firmware/pdk-ipc/${MCU_3_1_FW}"
ALTERNATIVE_TARGET[j721s2-c71_0-fw] = "${nonarch_base_libdir}/firmware/pdk-ipc/${C7X_1_FW}"
ALTERNATIVE_TARGET[j721s2-c71_1-fw] = "${nonarch_base_libdir}/firmware/pdk-ipc/${C7X_2_FW}"

ALTERNATIVE_TARGET[j721s2-main-r5f0_0-fw-sec] = "${base_libdir}/firmware/pdk-ipc/${MCU_2_0_FW}.signed"
ALTERNATIVE_TARGET[j721s2-main-r5f0_1-fw-sec] = "${base_libdir}/firmware/pdk-ipc/${MCU_2_1_FW}.signed"
ALTERNATIVE_TARGET[j721s2-main-r5f1_0-fw-sec] = "${base_libdir}/firmware/pdk-ipc/${MCU_3_0_FW}.signed"
ALTERNATIVE_TARGET[j721s2-main-r5f1_1-fw-sec] = "${base_libdir}/firmware/pdk-ipc/${MCU_3_1_FW}.signed"
ALTERNATIVE_TARGET[j721s2-c71_0-fw-sec] = "${base_libdir}/firmware/pdk-ipc/${C7X_1_FW}.signed"
ALTERNATIVE_TARGET[j721s2-c71_1-fw-sec] = "${base_libdir}/firmware/pdk-ipc/${C7X_2_FW}.signed"

ALTERNATIVE_TARGET[j784s4-mcu-r5f0_0-fw] = "${base_libdir}/firmware/pdk-ipc/${DM_FIRMWARE}"
ALTERNATIVE_TARGET[j784s4-mcu-r5f0_1-fw] = "${base_libdir}/firmware/pdk-ipc/${MCU_1_1_FW}"
ALTERNATIVE_TARGET[j784s4-main-r5f0_0-fw] = "${base_libdir}/firmware/pdk-ipc/${MCU_2_0_FW}"
ALTERNATIVE_TARGET[j784s4-main-r5f0_1-fw] = "${base_libdir}/firmware/pdk-ipc/${MCU_2_1_FW}"
ALTERNATIVE_TARGET[j784s4-main-r5f1_0-fw] = "${base_libdir}/firmware/pdk-ipc/${MCU_3_0_FW}"
ALTERNATIVE_TARGET[j784s4-main-r5f1_1-fw] = "${base_libdir}/firmware/pdk-ipc/${MCU_3_1_FW}"
ALTERNATIVE_TARGET[j784s4-main-r5f2_0-fw] = "${base_libdir}/firmware/pdk-ipc/${MCU_4_0_FW}"
ALTERNATIVE_TARGET[j784s4-main-r5f2_1-fw] = "${base_libdir}/firmware/pdk-ipc/${MCU_4_1_FW}"
ALTERNATIVE_TARGET[j784s4-c71_0-fw] = "${base_libdir}/firmware/pdk-ipc/${C7X_1_FW}"
ALTERNATIVE_TARGET[j784s4-c71_1-fw] = "${base_libdir}/firmware/pdk-ipc/${C7X_2_FW}"
ALTERNATIVE_TARGET[j784s4-c71_2-fw] = "${base_libdir}/firmware/pdk-ipc/${C7X_3_FW}"
ALTERNATIVE_TARGET[j784s4-c71_3-fw] = "${base_libdir}/firmware/pdk-ipc/${C7X_4_FW}"

ALTERNATIVE_PRIORITY = "10"

# make sure that lib/firmware, and all its contents are part of the package
FILES:${PN} += "${nonarch_base_libdir}/firmware"

# This is used to prevent the build system to_strip the executables
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
# This is used to prevent the build system to split the debug info in a separate file
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
# As it likely to be a different arch from the Yocto build, disable checking by adding "arch" to INSANE_SKIP
INSANE_SKIP:${PN} += "arch"

# we don't want to configure and build the source code
do_compile[noexec] = "1"
do_configure[noexec] = "1"

addtask deploy after do_install
