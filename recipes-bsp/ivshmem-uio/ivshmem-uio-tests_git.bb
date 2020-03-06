SUMMARY =  "UIO tests for IVSHMEM based UIO driver"
DESCRIPTION = "Test programs which use UIO (userspace io) device for inter VM communication"
HOMEPAGE = "https://github.com/henning-schild-work/ivshmem-guest-code"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/COPYING;md5=0546a27aad86c83b75ad4ee6133e9d5e"

inherit cmake

PROTOCOL = "git"
BRANCH = "jailhouse"
SRCREV = "f3ad79881bebb6c6068966ee3d265d8034c20492"
SRC_URI = "git://github.com/henning-schild-work/ivshmem-guest-code.git;protocol=${PROTOCOL};branch=${BRANCH}"

S = "${WORKDIR}/git/uio/tests/Interrupts/VM"

COMPATIBLE_MACHINE = "(ti-soc)"
OECMAKE_TARGET_COMPILE = "uio_send uio_read"

do_install() {
    install -d ${D}/${bindir}
    install -m 755 ${B}/uio_send ${D}/${bindir}/
    install -m 755 ${B}/uio_read ${D}/${bindir}/
}
