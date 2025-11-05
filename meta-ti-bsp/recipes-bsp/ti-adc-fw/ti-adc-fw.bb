DESCRIPTION = "TI ADC PCM6240 config prebuild binary firmware"

require recipes-bsp/ti-linux-fw/ti-linux-fw.inc

PV = "${PCM6240_FW_VERSION}"
PR = "${INC_PR}.0"

COMPATIBLE_MACHINE = "am62dxx"

PCM6240_2DEV = "pcm6240-2dev-reg.bin"

do_install() {
	install -d ${D}${nonarch_base_libdir}/firmware
	install -m 0644 ${S}/ti/pcm6240/${PCM6240_2DEV} ${D}${nonarch_base_libdir}/firmware/
}
