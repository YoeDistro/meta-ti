HOMEPAGE = "http://processors.wiki.ti.com/index.php/Category:CMEM"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://include/ti/cmem.h;beginline=1;endline=30;md5=b86138d4028fb8310b3b983024edc620"

BRANCH ?= "master"
# This corresponds to version 4.00.01.08
SRCREV = "0b68dfe9f155a1978cdb2178e052dc0d6f1e705b"

SRC_URI = "git://git.ti.com/ipc/ludev.git;protocol=git;branch=${BRANCH}"

S = "${WORKDIR}/git"
