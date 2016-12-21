HOMEPAGE = "http://processors.wiki.ti.com/index.php/Category:CMEM"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://include/ti/cmem.h;beginline=1;endline=30;md5=9b745e2765a6ba8a636c91aed4b02ac1"

BRANCH = "lu-next"
# This corresponds to version 4.13.00.00_eng
SRCREV = "1b1e19754049446c20fba7c22b39b325eb0154ec"

PV = "4.13.00.00+git${SRCPV}"

SRC_URI = "git://git.ti.com/ipc/ludev.git;protocol=git;branch=${BRANCH}"

S = "${WORKDIR}/git"
