SUMMARY = "TI RPMsg Char Utility Library"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${S}/src/rpmsg_char.c;beginline=1;endline=31;md5=71987db43a2159cff5ea00505c6fce58"

PROTOCOL = "https"
BRANCH = "master"
SRC_URI = "git://git.ti.com/git/rpmsg/ti-rpmsg-char.git;protocol=${PROTOCOL};branch=${BRANCH};"

# Initial 0.5.2 release
SRCREV = "1da3e1bd0412c148ab57690940fea5d129220896"

PV = "0.5.2+git${SRCPV}"

S = "${WORKDIR}/git"

COMPATIBLE_HOST ?= "null"
COMPATIBLE_HOST_ti-soc = "(.*)"
