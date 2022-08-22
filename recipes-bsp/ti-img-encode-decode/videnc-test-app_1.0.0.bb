SUMMARY = "Test applications for TI ENC (v4l2 encoder for IMG VXE384)"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://main.c;beginline=1;endline=14;md5=3545dd5bdf513840937d38c10b866605"

DEPENDS = "libdrm"

inherit autotools pkgconfig

COMPATIBLE_MACHINE = "j7-evm|j7-hs-evm"

PR = "r0"
SRCREV = "a5e7d820bea1be24f25a8369d4d4521c784f869a"

EXTRA_OEMAKE = "CC="${CC}""
TARGET_CC_ARCH += "${LDFLAGS}"

BRANCH = "master"
SRC_URI = "git://git.ti.com/git/jacinto7_multimedia/videnc-test-app.git;protocol=https;branch=${BRANCH}"

S = "${WORKDIR}/git"
