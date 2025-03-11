SUMMARY = "TI RPMsg Char Utility Library"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${S}/src/rpmsg_char.c;beginline=1;endline=31;md5=71987db43a2159cff5ea00505c6fce58"

PROTOCOL = "https"
BRANCH = "master"
SRC_URI = "git://git.ti.com/git/rpmsg/ti-rpmsg-char.git;protocol=${PROTOCOL};branch=${BRANCH};"

# 0.6.7 release
SRCREV = "5100a2e4a4a951e40ea0708fa78960770ec0ac33"

PV = "0.6.7+git"

S = "${WORKDIR}/git"

COMPATIBLE_MACHINE = "^(k3)$"
