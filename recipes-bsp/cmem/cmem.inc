HOMEPAGE = "http://processors.wiki.ti.com/index.php/Category:CMEM"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://include/ti/cmem.h;beginline=1;endline=30;md5=9b745e2765a6ba8a636c91aed4b02ac1"

BRANCH = "lu-next"
# This corresponds to version 4.14.00.00_eng
SRCREV = "b514a99ac45c64f0439282d0774d7cff5def4c6f"

PV = "4.14.00.00+git${SRCPV}"

SRC_URI = "git://git.ti.com/ipc/ludev.git;protocol=git;branch=${BRANCH}"

S = "${WORKDIR}/git"
