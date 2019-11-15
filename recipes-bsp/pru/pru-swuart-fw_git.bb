SUMMARY = "Programmable Real-time Unit Software UART Firmware"
LICENSE = "BSD-3-Clause"

LIC_FILES_CHKSUM = "file://COPYING.txt;beginline=1;endline=31;md5=94b6a199da1caf777f6756cb70aca4a7"

require recipes-ti/includes/ti-paths.inc

COMPATIBLE_MACHINE = "ti33x"
PACKAGE_ARCH = "${MACHINE_ARCH}"


inherit ti-pdk-fetch

TI_PDK_COMP = "ti.drv.uart"

PE = "1"

SRC_URI_append = " \
	file://0001-icss_uart-add-Makefile-for-building-firmware.patch \
	file://0001-icss_uart-remove-dependency-on-PDK-CSL.patch \
"

DEPENDS = "ti-cgt-pru-native pru-icss"


export PRU_CGT = "${TI_CGT_PRU_INSTALL_DIR}"
export PRU_SSP = "${STAGING_DIR_TARGET}/usr"

do_compile() {
	oe_runmake -C firmware/icss_uart/src
}

do_install() {
	install -d ${D}/lib/firmware/ti-pruss
	install -m 0644 ${S}/firmware/icss_uart/src/gen/src.out ${D}/lib/firmware/ti-pruss/pru_swuart-fw.elf
}

FILES_${PN} = "/lib/firmware"

INSANE_SKIP_${PN} = "arch"
