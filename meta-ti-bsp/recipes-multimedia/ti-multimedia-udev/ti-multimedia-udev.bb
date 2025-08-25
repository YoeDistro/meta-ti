SUMMARY = "Udev rules for device naming of VPU devices and JPEG encoder"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI += "file://67-multimedia.rules"

S = "${UNPACKDIR}"

do_install() {
    install -d ${D}${sysconfdir}/udev/rules.d
    install -m 0644 ${UNPACKDIR}/67-multimedia.rules ${D}${sysconfdir}/udev/rules.d/
}
