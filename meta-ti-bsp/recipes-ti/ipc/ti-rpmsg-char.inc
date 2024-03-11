SUMMARY = "TI RPMsg Char Utility Library"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${S}/src/rpmsg_char.c;beginline=1;endline=31;md5=71987db43a2159cff5ea00505c6fce58"

PROTOCOL = "https"
BRANCH = "master"
SRC_URI = "git://git.ti.com/git/rpmsg/ti-rpmsg-char.git;protocol=${PROTOCOL};branch=${BRANCH};"

# 0.6.6 release
SRCREV = "55a6f4032246631a309f8608af5e822024664b59"

PV = "0.6.6+git${SRCPV}"

S = "${WORKDIR}/git"

COMPATIBLE_HOST ?= "null"
COMPATIBLE_HOST:ti-soc = "(.*)"
