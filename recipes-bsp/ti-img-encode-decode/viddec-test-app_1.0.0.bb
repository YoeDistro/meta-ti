SUMMARY = "Test applications for TI DEC (v4l2 decoder for IMG D5520)"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://main.c;beginline=1;endline=14;md5=f17e17d664f72942124e4fcf06c178ee"

DEPENDS = "libdrm ffmpeg"

inherit autotools pkgconfig

PR = "r0"
SRCREV = "9d5b3c11ac24dbed9284e849c0db9daeb47704e7"

EXTRA_OEMAKE = "CC="${CC}""
TARGET_CC_ARCH += "${LDFLAGS}"

BRANCH = "master"
SRC_URI = "git://git.ti.com/jacinto7_multimedia/viddec-test-app.git;protocol=git;branch=${BRANCH}"

S = "${WORKDIR}/git"
