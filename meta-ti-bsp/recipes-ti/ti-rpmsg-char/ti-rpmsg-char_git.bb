SUMMARY = "TI RPMsg Char Utility Library"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${S}/src/rpmsg_char.c;beginline=1;endline=31;md5=71987db43a2159cff5ea00505c6fce58"

PROTOCOL = "https"
BRANCH = "master"
SRC_URI = "git://git.ti.com/git/rpmsg/ti-rpmsg-char.git;protocol=${PROTOCOL};branch=${BRANCH};"

# 0.6.11 release
SRCREV = "b1e4d172f39081db81305fa50ccaafd3ef8ae3a6"

PV = "0.6.11+git"
PR = "r0"

COMPATIBLE_MACHINE = "^(k3)$"

inherit cmake

PACKAGES =+ "${PN}-examples"
FILES:${PN}-examples += "${bindir}/rpmsg_char_simple"
FILES:${PN}-examples += "${bindir}/rpmsg_char_benchmark"
RDEPENDS:${PN}-examples += "${PN}"
