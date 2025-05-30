SUMMARY = "Chips&Media codec firmware files"

require recipes-bsp/ti-linux-fw/ti-linux-fw.inc

inherit update-alternatives

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENCE.cnm;md5=df3992006621b797e36de43f36336e36"

PV = "${CNM_WAVE521_FW_VERSION}"
PR = "${INC_PR}.1"

COMPATIBLE_MACHINE = "j721s2|j784s4|j722s|j742s2|am62axx|am62pxx"

TARGET_WAVE521C = "wave521c_k3_codec_fw.bin"

SOURCE_WAVE521C = "wave521c_k3_codec_fw.bin"

ALTERNATIVE_LINK_NAME[wave521c_codec_fw.bin] = "${nonarch_base_libdir}/firmware/${TARGET_WAVE521C}"
ALTERNATIVE_TARGET[wave521c_codec_fw.bin] = "${nonarch_base_libdir}/firmware/cnm/${TARGET_WAVE521C}"
ALTERNATIVE_PRIORITY = "10"

# Set up names for the firmwares
ALTERNATIVE:${PN} = "wave521c_codec_fw.bin"

do_install() {
	install -d ${D}${nonarch_base_libdir}/firmware/cnm
	install -m 0644 ${S}/cnm/${SOURCE_WAVE521C} ${D}${nonarch_base_libdir}/firmware/cnm/${TARGET_WAVE521C}
}
