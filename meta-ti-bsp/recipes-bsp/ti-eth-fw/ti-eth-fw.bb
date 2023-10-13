SUMMARY = "TI Ethernet prebuilt binary firmware images"

LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://${COREBASE}/../meta-ti/licenses/TI-TFL;md5=a1b59cb7ba626b9dbbcbf00f3fbc438a"

COMPATIBLE_MACHINE = "k3"

PACKAGE_ARCH = "${MACHINE_ARCH}"

INHIBIT_DEFAULT_DEPS = "1"

inherit deploy
inherit update-alternatives

require recipes-bsp/ti-linux-fw/ti-linux-fw.inc

PV = "${CORESDK_RTOS_VERSION}"
PR = "${INC_PR}.0"

CLEANBROKEN = "1"

# Secure Build
inherit ti-secdev

PLAT_SFX = ""
PLAT_SFX:j721e = "j721e"
PLAT_SFX:j7200 = "j7200"
PLAT_SFX:j721s2 = "j721s2"
PLAT_SFX:j784s4 = "j784s4"
PLAT_SFX:am65xx = "am65xx"
PLAT_SFX:am64xx = "am64xx"
PLAT_SFX:am62xx = "am62xx"
PLAT_SFX:am62axx = "am62axx"

ETH_FW_DIR = "ti-eth/${PLAT_SFX}"

INSTALL_ETH_FW_DIR = "${nonarch_base_libdir}/firmware/${ETH_FW_DIR}"

ETH_FW = "app_remoteswitchcfg_server_strip.xer5f"

ETH_FW_LIST = ""
ETH_FW_LIST:j721e =   "${ETH_FW}"
ETH_FW_LIST:j7200 =   "${ETH_FW}"
ETH_FW_LIST:j721s2 =  ""
ETH_FW_LIST:j784s4 =  "${ETH_FW}"
ETH_FW_LIST:am65xx =  ""
ETH_FW_LIST:am64xx =  ""
ETH_FW_LIST:am62xx =  ""
ETH_FW_LIST:am62axx = ""

do_install() {
    # ETH firmware
    for FW_NAME in ${ETH_FW_LIST}
    do
        ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh ${S}/${ETH_FW_DIR}/${FW_NAME} ${S}/${ETH_FW_DIR}/${FW_NAME}.signed
    done

    # ETH firmware
    install -d ${D}${INSTALL_ETH_FW_DIR}
    for FW_NAME in ${ETH_FW_LIST}
    do
        install -m 0644 ${S}/${ETH_FW_DIR}/${FW_NAME}        ${D}${INSTALL_ETH_FW_DIR}
        install -m 0644 ${S}/${ETH_FW_DIR}/${FW_NAME}.signed ${D}${INSTALL_ETH_FW_DIR}
    done
}

# Set up names for the firmwares
ALTERNATIVE:${PN}:j721e = "\
                    j7-main-r5f0_0-fw \
                    j7-main-r5f0_0-fw-sec \
                    "

ALTERNATIVE:${PN}:j7200 = "\
                    j7200-main-r5f0_0-fw \
                    j7200-main-r5f0_0-fw-sec \
                    "

ALTERNATIVE:${PN}:j784s4 = "\
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
ALTERNATIVE_TARGET[j7-main-r5f0_0-fw]        = "${INSTALL_ETH_FW_DIR}/${ETH_FW}"
ALTERNATIVE_TARGET[j7-main-r5f0_0-fw-sec]    = "${INSTALL_ETH_FW_DIR}/${ETH_FW}.signed"

ALTERNATIVE_TARGET[j7200-main-r5f0_0-fw]     = "${INSTALL_ETH_FW_DIR}/${ETH_FW}"
ALTERNATIVE_TARGET[j7200-main-r5f0_0-fw-sec] = "${INSTALL_ETH_FW_DIR}/${ETH_FW}.signed"

ALTERNATIVE_TARGET[j784s4-main-r5f0_0-fw]     = "${INSTALL_ETH_FW_DIR}/${ETH_FW}"
ALTERNATIVE_TARGET[j784s4-main-r5f0_0-fw-sec] = "${INSTALL_ETH_FW_DIR}/${ETH_FW}.signed"

ALTERNATIVE_PRIORITY = "5"

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
