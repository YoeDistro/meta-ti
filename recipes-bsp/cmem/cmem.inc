HOMEPAGE = "http://processors.wiki.ti.com/index.php/Category:CMEM"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://include/ti/cmem.h;beginline=1;endline=30;md5=26be509e4bb413905bda8309e338e2b1"

BRANCH = "lu-next"
# This corresponds to version 4.15.00.00_eng
SRCREV = "86462821d99cf0e5c4ad33158e08feeb01b26484"

PV = "4.15.00.00+git${SRCPV}"

SRC_URI = "git://git.ti.com/ipc/ludev.git;protocol=git;branch=${BRANCH}"

S = "${WORKDIR}/git"
