SUMMARY = "TI RPMsg Char Utility Library"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${S}/src/rpmsg_char.c;beginline=1;endline=31;md5=71987db43a2159cff5ea00505c6fce58"

PROTOCOL = "git"
BRANCH = "master"
SRC_URI = "git://git.ti.com/rpmsg/ti-rpmsg-char.git;protocol=${PROTOCOL};branch=${BRANCH};"

# Initial 0.3.0 release
SRCREV = "0e76d13d08f0df5c7e70d58bbf76f7e299906ae1"

PV = "0.3.0+git${SRCPV}"

S = "${WORKDIR}/git"

COMPATIBLE_HOST ?= "null"
COMPATIBLE_HOST_ti-soc = "(.*)"
