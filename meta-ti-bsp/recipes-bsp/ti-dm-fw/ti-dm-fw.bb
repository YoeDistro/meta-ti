SUMMARY = "TI DM prebuilt binary firmware images"

LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://${THISDIR}/../../licenses/TI-TFL;md5=a1b59cb7ba626b9dbbcbf00f3fbc438a"

COMPATIBLE_MACHINE = "k3"

PACKAGE_ARCH = "${MACHINE_ARCH}"

INHIBIT_DEFAULT_DEPS = "1"

inherit deploy
inherit update-alternatives

require recipes-bsp/ti-linux-fw/ti-linux-fw.inc

PV = "${TI_DM_FW_VERSION}"
PR = "${INC_PR}.0"

# Secure Build
inherit ti-secdev

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
PLAT_SFX:am62lxx = "am62lxx"
PLAT_SFX:am62pxx = "am62pxx"

DM_FW_DIR = "ti-dm/${PLAT_SFX}"

INSTALL_DM_FW_DIR  = "${nonarch_base_libdir}/firmware/${DM_FW_DIR}"

DM_FIRMWARE = "ipc_echo_testb_mcu1_0_release_strip.xer5f"

DM_FW_LIST = ""
DM_FW_LIST:j721e =   "${DM_FIRMWARE}"
DM_FW_LIST:j7200 =   "${DM_FIRMWARE}"
DM_FW_LIST:j721s2 =  "${DM_FIRMWARE}"
DM_FW_LIST:j784s4 =  "${DM_FIRMWARE}"
DM_FW_LIST:j722s =   "${DM_FIRMWARE}"
DM_FW_LIST:j742s2 =  "${DM_FIRMWARE}"
DM_FW_LIST:am65xx =  ""
DM_FW_LIST:am64xx =  ""
DM_FW_LIST:am62xx =  "${DM_FIRMWARE}"
DM_FW_LIST:am62axx = "${DM_FIRMWARE}"
DM_FW_LIST:am62lxx = ""
DM_FW_LIST:am62pxx = "${DM_FIRMWARE}"

do_install() {
    # Sign the firmware
    # DM Firmware
    for FW_NAME in ${DM_FW_LIST}
    do
        ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh ${S}/${DM_FW_DIR}/${FW_NAME} ${S}/${DM_FW_DIR}/${FW_NAME}.signed
    done

    # DM Firmware
    install -d ${D}${INSTALL_DM_FW_DIR}
    for FW_NAME in ${DM_FW_LIST}
    do
        install -m 0644 ${S}/${DM_FW_DIR}/${FW_NAME}        ${D}${INSTALL_DM_FW_DIR}/
        install -m 0644 ${S}/${DM_FW_DIR}/${FW_NAME}.signed ${D}${INSTALL_DM_FW_DIR}/
    done
}

do_deploy() {
    # DM Firmware is needed for rebuilding U-Boot
    install -d ${DEPLOYDIR}/${DM_FW_DIR}
    for FW_NAME in ${DM_FW_LIST}
    do
        install -m 0644 ${S}/${DM_FW_DIR}/${FW_NAME}        ${DEPLOYDIR}/${DM_FW_DIR}
        install -m 0644 ${S}/${DM_FW_DIR}/${FW_NAME}.signed ${DEPLOYDIR}/${DM_FW_DIR}
    done
}

# Set up names for the firmwares
ALTERNATIVE:${PN}:am62xx  = "am62-main-r5f0_0-fw"
ALTERNATIVE:${PN}:am62pxx  = "am62p-main-r5f0_0-fw"
ALTERNATIVE:${PN}:am62axx = "am62a-main-r5f0_0-fw"
ALTERNATIVE:${PN}:j721e   = "j7-mcu-r5f0_0-fw"
ALTERNATIVE:${PN}:j7200   = "j7200-mcu-r5f0_0-fw"
ALTERNATIVE:${PN}:j721s2  = "j721s2-mcu-r5f0_0-fw"
ALTERNATIVE:${PN}:j784s4  = "j784s4-mcu-r5f0_0-fw"
ALTERNATIVE:${PN}:j722s   = "j722s-wkup-r5f0_0-fw"
ALTERNATIVE:${PN}:j742s2  = "j742s2-mcu-r5f0_0-fw"

# Set up link names for the firmwares
ALTERNATIVE_LINK_NAME[am62-main-r5f0_0-fw]  = "${nonarch_base_libdir}/firmware/am62-main-r5f0_0-fw"
ALTERNATIVE_LINK_NAME[am62p-main-r5f0_0-fw]  = "${nonarch_base_libdir}/firmware/am62p-main-r5f0_0-fw"
ALTERNATIVE_LINK_NAME[am62a-main-r5f0_0-fw] = "${nonarch_base_libdir}/firmware/am62a-main-r5f0_0-fw"
ALTERNATIVE_LINK_NAME[j7-mcu-r5f0_0-fw]     = "${nonarch_base_libdir}/firmware/j7-mcu-r5f0_0-fw"
ALTERNATIVE_LINK_NAME[j7200-mcu-r5f0_0-fw]  = "${nonarch_base_libdir}/firmware/j7200-mcu-r5f0_0-fw"
ALTERNATIVE_LINK_NAME[j721s2-mcu-r5f0_0-fw] = "${nonarch_base_libdir}/firmware/j721s2-mcu-r5f0_0-fw"
ALTERNATIVE_LINK_NAME[j784s4-mcu-r5f0_0-fw] = "${nonarch_base_libdir}/firmware/j784s4-mcu-r5f0_0-fw"
ALTERNATIVE_LINK_NAME[j722s-wkup-r5f0_0-fw] = "${nonarch_base_libdir}/firmware/j722s-wkup-r5f0_0-fw"
ALTERNATIVE_LINK_NAME[j742s2-mcu-r5f0_0-fw] = "${nonarch_base_libdir}/firmware/j742s2-mcu-r5f0_0-fw"

# Create the firmware alternatives
ALTERNATIVE_TARGET[am62-main-r5f0_0-fw]  = "${INSTALL_DM_FW_DIR}/${DM_FIRMWARE}"
ALTERNATIVE_TARGET[am62p-main-r5f0_0-fw]  = "${INSTALL_DM_FW_DIR}/${DM_FIRMWARE}"
ALTERNATIVE_TARGET[am62a-main-r5f0_0-fw] = "${INSTALL_DM_FW_DIR}/${DM_FIRMWARE}"
ALTERNATIVE_TARGET[j7-mcu-r5f0_0-fw]     = "${INSTALL_DM_FW_DIR}/${DM_FIRMWARE}"
ALTERNATIVE_TARGET[j7200-mcu-r5f0_0-fw]  = "${INSTALL_DM_FW_DIR}/${DM_FIRMWARE}"
ALTERNATIVE_TARGET[j721s2-mcu-r5f0_0-fw] = "${INSTALL_DM_FW_DIR}/${DM_FIRMWARE}"
ALTERNATIVE_TARGET[j784s4-mcu-r5f0_0-fw] = "${INSTALL_DM_FW_DIR}/${DM_FIRMWARE}"
ALTERNATIVE_TARGET[j722s-wkup-r5f0_0-fw] = "${INSTALL_DM_FW_DIR}/${DM_FIRMWARE}"
ALTERNATIVE_TARGET[j742s2-mcu-r5f0_0-fw] = "${INSTALL_DM_FW_DIR}/${DM_FIRMWARE}"

ALTERNATIVE_PRIORITY = "10"

addtask deploy after do_install
