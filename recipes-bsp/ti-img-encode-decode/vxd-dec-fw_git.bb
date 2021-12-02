SUMMARY = "Video Decoding Firmware"
LICENSE = "TI-IMG"
LIC_FILES_CHKSUM = "file://LICENSE.ti-img;md5=84ca7278930db001870686ad997d6bb1"

require recipes-bsp/ti-linux-fw/ti-linux-fw.inc

PV = "${IMG_DEC_FW_VERSION}"
PR = "${INC_PR}.0"

CLEANBROKEN = "1"

COMPATIBLE_MACHINE = "j7"

PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}/git"
TARGET = "pvdec_full_bin.fw"

do_install() {
        install -d ${D}${base_libdir}/firmware
        install -m 0644 ${S}/ti-img/${TARGET} ${D}${base_libdir}/firmware/${TARGET}
}

FILES_${PN} = "${base_libdir}/firmware"
