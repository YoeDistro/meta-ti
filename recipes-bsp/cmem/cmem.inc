HOMEPAGE = "http://processors.wiki.ti.com/index.php/Category:CMEM"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://include/ti/cmem.h;beginline=1;endline=30;md5=26be509e4bb413905bda8309e338e2b1"

BRANCH = "master"
# This corresponds to version 4.20.00.01
SRCREV = "86269258a48e0a9008dd9d5ebfae9da7ce843393"

PV = "4.20.00.01+git${SRCPV}"

SRC_URI = "git://git.ti.com/git/ipc/ludev.git;protocol=https;branch=${BRANCH}"

S = "${WORKDIR}/git"
COMPATIBLE_HOST ?= "null"
COMPATIBLE_HOST_ti-soc = "(.*)"
