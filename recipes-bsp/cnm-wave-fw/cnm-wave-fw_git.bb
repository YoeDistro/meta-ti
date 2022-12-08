SUMMARY = "Chips&Media codec firmware files"

require recipes-bsp/ti-linux-fw/ti-linux-fw.inc

inherit deploy
inherit update-alternatives

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENCE.cnm;md5=93b67e6bac7f8fec22b96b8ad0a1a9d0"

PV = "${CNM_WAVE521_FW_VERSION}"
PR = "${INC_PR}.1"

CLEANBROKEN = "1"

COMPATIBLE_MACHINE = "j721s2-evm|j721s2-hs-evm|j784s4-evm|j784s4-hs-evm|am62axx"

PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}/git"
TARGET_WAVE521C = "wave521c_codec_fw.bin"

SOURCE_WAVE521C = "wave521c_k3_codec_fw.bin"

ALTERNATIVE_LINK_NAME[wave521c_codec_fw.bin] = "${base_libdir}/firmware/${TARGET_WAVE521C}"
ALTERNATIVE_TARGET[wave521c_codec_fw.bin] = "${base_libdir}/firmware/cnm/${TARGET_WAVE521C}"
ALTERNATIVE_PRIORITY = "10"

# Set up names for the firmwares
ALTERNATIVE_${PN} = "\
                    wave521c_codec_fw.bin"

do_install() {
	install -d ${D}${base_libdir}/firmware/cnm
	install -m 0644 ${S}/cnm/${SOURCE_WAVE521C} ${D}${base_libdir}/firmware/cnm/${TARGET_WAVE521C}
}

# make sure that lib/firmware, and all its contents are part of the package
FILES_${PN} = "${base_libdir}/firmware"

# we don't want to configure and build the source code
do_compile[noexec] = "1"
do_configure[noexec] = "1"
