DESCRIPTION = "Goodix GT9271 config firmware"

require recipes-bsp/ti-linux-fw/ti-linux-fw.inc

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENCE.Spectrum-GT9271;md5=2a6de6be7af1fe46370c684daf27c852"

PV = "${GOODIX_FW_VERSION}"
PR = "${INC_PR}.0"

COMPATIBLE_MACHINE = "dra7xx"

ORIGIN = "DRA71x-RevA-GT9271_SpecDig_Config.bin"
TARGET = "goodix_9271_cfg.bin"

do_install() {
	install -d ${D}${nonarch_base_libdir}/firmware
	install -m 0644 ${S}/ti-evm/${ORIGIN} ${D}${nonarch_base_libdir}/firmware/${TARGET}
}
