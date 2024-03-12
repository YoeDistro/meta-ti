SUMMARY = "Video Decoding Firmware"
LICENSE = "TI-IMG"
LIC_FILES_CHKSUM = "file://LICENSE.ti-img;md5=84ca7278930db001870686ad997d6bb1"

require recipes-bsp/ti-linux-fw/ti-linux-fw.inc

PV = "${IMG_DEC_FW_VERSION}"
PR = "${INC_PR}.0"

COMPATIBLE_MACHINE = "j721e"

TARGET = "pvdec_full_bin.fw"

do_install() {
        install -d ${D}${nonarch_base_libdir}/firmware
        install -m 0644 ${S}/ti-img/${TARGET} ${D}${nonarch_base_libdir}/firmware/${TARGET}
}
