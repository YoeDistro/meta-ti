DESCRIPTION = "PRU Switch firmware for AM57xx"

require recipes-ti/includes/ti-paths.inc
require recipes-bsp/icss-emac-lld/icss-emac-lld.inc

LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://icss_dualemac/src/firmware_version.h;beginline=6;endline=53;md5=1f0a640a261059cdcbbcf01e6a739ff3"

PR = "${INC_PR}.0"

TI_PDK_COMP = "ti.drv.icss_emac.firmware"

B = "${S}/icss_switch"

COMPATIBLE_MACHINE = "dra7xx"

PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS = "ti-cgt-pru-native"

PRU_VERSION = ""
PRU_VERSION_dra7xx = "REV2"

FW_PREFIX = ""
FW_PREFIX_dra7xx = "am57xx"


EXTRA_OEMAKE += "CL_PRU_INSTALL_PATH="${TI_CGT_PRU_INSTALL_DIR}" PRU_VERSION_LIST="${PRU_VERSION}""

do_install() {
	install -d ${D}${base_libdir}/firmware/ti-pruss
	install -m 0644 ./elf/${PRU_VERSION}/icss_stp_switch_PRU0.elf \
		${D}${base_libdir}/firmware/ti-pruss/${FW_PREFIX}-pru0-prusw-fw.elf
	install -m 0644 ./elf/${PRU_VERSION}/icss_stp_switch_PRU1.elf \
		${D}${base_libdir}/firmware/ti-pruss/${FW_PREFIX}-pru1-prusw-fw.elf
}

FILES_${PN} = "${base_libdir}/firmware"

INSANE_SKIP_${PN} = "arch"
