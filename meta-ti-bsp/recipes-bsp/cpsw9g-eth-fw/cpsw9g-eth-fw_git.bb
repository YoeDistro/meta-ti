SUMMARY = "R5 PSDK CPSW9G Ethernet Switch Firmware"

LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://LICENSE.ti;md5=04ad0a015d4bb63c2b9e7b112debf3db"

PV = "6.2+git${SRCPV}"
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit update-alternatives

PROTOCOL = "https"
BRANCH = "master"
SRCREV = "91f1628507bf7f8716f0bc7cafe88ad7f14c94f5"

SRC_URI = "git://git.ti.com/git/glsdk/ti-eth-fw.git;protocol=${PROTOCOL};branch=${BRANCH}"

S = "${WORKDIR}/git"

CPSW9G_FW_DIR = "${S}/ethfw"
CPSW9G_FW_FILENAME = "app_remoteswitchcfg_server_pdk_mem_map_strip.xer5f"

# make sure that lib/firmware, and all its contents are part of the package
FILES:${PN} += "${nonarch_base_libdir}/firmware"

do_install() {
  install -d ${D}${nonarch_base_libdir}/firmware
# Ethernet firmware to be loaded on Main R5 core(needs read permission)
  install -m 0644 ${CPSW9G_FW_DIR}/${CPSW9G_FW_FILENAME} ${D}${nonarch_base_libdir}/firmware
}

TARGET_MAIN_R5FSS0_0 = "j7-main-r5f0_0-fw"
ALTERNATIVE:${PN} = "j7-main-r5f0_0-fw"
ALTERNATIVE_LINK_NAME[j7-main-r5f0_0-fw] = "${nonarch_base_libdir}/firmware/${TARGET_MAIN_R5FSS0_0}"
ALTERNATIVE_TARGET[j7-main-r5f0_0-fw] = "${nonarch_base_libdir}/firmware/${CPSW9G_FW_FILENAME}"
ALTERNATIVE_PRIORITY = "17"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INSANE_SKIP:${PN} += "arch"

do_compile[noexec] = "1"
do_configure[noexec] = "1"
