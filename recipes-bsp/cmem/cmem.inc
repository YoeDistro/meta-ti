HOMEPAGE = "http://processors.wiki.ti.com/index.php/Category:CMEM"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://include/ti/cmem.h;beginline=1;endline=30;md5=9b745e2765a6ba8a636c91aed4b02ac1"

BRANCH ?= "lu-next"
# This corresponds to version 4.11.00.00_eng
SRCREV = "852e97b5584cbdb6aa0fdef02a7c4e245409bcb6"

PV = "4.11.00.00+git${SRCPV}"

SRC_URI = "git://git.ti.com/ipc/ludev.git;protocol=git;branch=${BRANCH}"

S = "${WORKDIR}/git"
