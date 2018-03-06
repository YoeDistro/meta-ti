HOMEPAGE = "http://processors.wiki.ti.com/index.php/Category:CMEM"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://include/ti/cmem.h;beginline=1;endline=30;md5=26be509e4bb413905bda8309e338e2b1"

BRANCH = "master"
# This corresponds to version 4.15.00.01
SRCREV = "000038a65af9428b559de5e00e4a7588892ad7b0"

PV = "4.15.00.01+git${SRCPV}"

SRC_URI = "git://git.ti.com/ipc/ludev.git;protocol=git;branch=${BRANCH}"

S = "${WORKDIR}/git"
