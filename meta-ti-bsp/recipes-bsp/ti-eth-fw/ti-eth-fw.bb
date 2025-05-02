SUMMARY = "TI Ethernet prebuilt binary firmware images"

LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://${THISDIR}/../../licenses/TI-TFL;md5=a1b59cb7ba626b9dbbcbf00f3fbc438a"

COMPATIBLE_MACHINE = "j721e|j7200|j784s4"

inherit update-alternatives

require recipes-bsp/ti-linux-fw/ti-linux-fw.inc

PV = "${CORESDK_RTOS_VERSION}"
PR = "${INC_PR}.0"

# Secure Build
inherit ti-secdev

PACKAGES += " \
    ${PN}-j721e \
    ${PN}-j7200 \
    ${PN}-j784s4 \
"
# Disable arch checking as firmware is likely to be a different arch from the Yocto build
INSANE_SKIP:${PN}-j721e += "arch"
INSANE_SKIP:${PN}-j7200 += "arch"
INSANE_SKIP:${PN}-j784s4 += "arch"

RDEPENDS:${PN} += " \
    ${PN}-j721e \
    ${PN}-j7200 \
    ${PN}-j784s4 \
"

PLATS = "\
    j721e \
    j7200 \
    j784s4 \
"

ETH_FW = "app_remoteswitchcfg_server_strip.xer5f"

do_install() {
    for PLAT in ${PLATS}
    do
        # Sign ETH firmware
        ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh ${S}/ti-eth/${PLAT}/${ETH_FW} ${S}/ti-eth/${PLAT}/${ETH_FW}.signed

        # Install ETH firmware
        install -d ${D}${nonarch_base_libdir}/firmware/ti-eth/${PLAT}
        install -m 0644 ${S}/ti-eth/${PLAT}/${ETH_FW}        ${D}${nonarch_base_libdir}/firmware/ti-eth/${PLAT}
        install -m 0644 ${S}/ti-eth/${PLAT}/${ETH_FW}.signed ${D}${nonarch_base_libdir}/firmware/ti-eth/${PLAT}
    done
}

FILES:${PN} = ""
FILES:${PN}-j721e = "${nonarch_base_libdir}/firmware/ti-eth/j721e"
FILES:${PN}-j7200 = "${nonarch_base_libdir}/firmware/ti-eth/j7200"
FILES:${PN}-j784s4 = "${nonarch_base_libdir}/firmware/ti-eth/j784s4"

# Set up names for the firmwares
ALTERNATIVE:${PN}-j721e = "\
                    j7-main-r5f0_0-fw \
                    j7-main-r5f0_0-fw-sec \
                    "

ALTERNATIVE:${PN}-j7200 = "\
                    j7200-main-r5f0_0-fw \
                    j7200-main-r5f0_0-fw-sec \
                    "

ALTERNATIVE:${PN}-j784s4 = "\
                    j784s4-main-r5f0_0-fw \
                    j784s4-main-r5f0_0-fw-sec \
                    "

# Set up link names for the firmwares
ALTERNATIVE_LINK_NAME[j7-main-r5f0_0-fw]        = "${nonarch_base_libdir}/firmware/j7-main-r5f0_0-fw"
ALTERNATIVE_LINK_NAME[j7-main-r5f0_0-fw-sec]    = "${nonarch_base_libdir}/firmware/j7-main-r5f0_0-fw-sec"

ALTERNATIVE_LINK_NAME[j7200-main-r5f0_0-fw]     = "${nonarch_base_libdir}/firmware/j7200-main-r5f0_0-fw"
ALTERNATIVE_LINK_NAME[j7200-main-r5f0_0-fw-sec] = "${nonarch_base_libdir}/firmware/j7200-main-r5f0_0-fw-sec"

ALTERNATIVE_LINK_NAME[j784s4-main-r5f0_0-fw]     = "${nonarch_base_libdir}/firmware/j784s4-main-r5f0_0-fw"
ALTERNATIVE_LINK_NAME[j784s4-main-r5f0_0-fw-sec] = "${nonarch_base_libdir}/firmware/j784s4-main-r5f0_0-fw-sec"

# Create the firmware alternatives
ALTERNATIVE_TARGET[j7-main-r5f0_0-fw]        = "${nonarch_base_libdir}/firmware/ti-eth/j721e/${ETH_FW}"
ALTERNATIVE_TARGET[j7-main-r5f0_0-fw-sec]    = "${nonarch_base_libdir}/firmware/ti-eth/j721e/${ETH_FW}.signed"

ALTERNATIVE_TARGET[j7200-main-r5f0_0-fw]     = "${nonarch_base_libdir}/firmware/ti-eth/j7200/${ETH_FW}"
ALTERNATIVE_TARGET[j7200-main-r5f0_0-fw-sec] = "${nonarch_base_libdir}/firmware/ti-eth/j7200/${ETH_FW}.signed"

ALTERNATIVE_TARGET[j784s4-main-r5f0_0-fw]     = "${nonarch_base_libdir}/firmware/ti-eth/j784s4/${ETH_FW}"
ALTERNATIVE_TARGET[j784s4-main-r5f0_0-fw-sec] = "${nonarch_base_libdir}/firmware/ti-eth/j784s4/${ETH_FW}.signed"

ALTERNATIVE_PRIORITY = "5"
