SUMMARY = "TI RPMsg Char Utility Library"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${S}/src/rpmsg_char.c;beginline=1;endline=31;md5=71987db43a2159cff5ea00505c6fce58"

PROTOCOL = "https"
BRANCH = "master"
SRC_URI = "git://git.ti.com/git/rpmsg/ti-rpmsg-char.git;protocol=${PROTOCOL};branch=${BRANCH};"

# 0.6.9 release
SRCREV = "09b064c6663ed46e4a402291dd87c8fe96135bd7"

PV = "0.6.9+git"

COMPATIBLE_MACHINE = "^(k3)$"
