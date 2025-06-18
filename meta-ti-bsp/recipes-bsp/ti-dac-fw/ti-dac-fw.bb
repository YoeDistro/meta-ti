DESCRIPTION = "TI DAC TAD5212 config prebuild binary firmware"

require recipes-bsp/ti-linux-fw/ti-linux-fw.inc

PV = "${TAD5212_FW_VERSION}"
PR = "${INC_PR}.0"

COMPATIBLE_MACHINE = "am62dxx"

TAD5212_1DEV = "tad5212_01.bin"
TAD5212_4DEV = "tad5212_04.bin"

do_install() {
	install -d ${D}${nonarch_base_libdir}/firmware
	install -m 0644 ${S}/ti/tad5212/${TAD5212_1DEV} ${D}${nonarch_base_libdir}/firmware/
	install -m 0644 ${S}/ti/tad5212/${TAD5212_4DEV} ${D}${nonarch_base_libdir}/firmware/
}
