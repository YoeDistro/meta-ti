SUMMARY = "TI RPMsg Char Utility Library"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${S}/src/rpmsg_char.c;beginline=1;endline=31;md5=71987db43a2159cff5ea00505c6fce58"

PROTOCOL = "git"
BRANCH = "master"
SRC_URI = "git://git.ti.com/rpmsg/ti-rpmsg-char.git;protocol=${PROTOCOL};branch=${BRANCH};"

# Initial 0.1.0 release
SRCREV = "9be402c2bf4053900b6b9a8092fbb42e7e303108"

PV = "0.1.0+git${SRCPV}"

S = "${WORKDIR}/git"
