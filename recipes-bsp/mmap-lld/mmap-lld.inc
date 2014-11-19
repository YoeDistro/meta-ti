LICENSE = "BSD-3-Clause"

COMPATIBLE_MACHINE = "keystone"

LIC_FILES_CHKSUM = "file://COPYING.txt;md5=b3315b8190036fe1c6a8263d02e15606"


BRANCH="master"
SRC_URI = "git://git.ti.com/keystone-rtos/mmap-lld.git;destsuffix=git/ti/runtime/mmap;protocol=git;branch=${BRANCH}"
# Commit correspond to DEV.MMAP.02.00.00.03
SRCREV = "601f9284c392f67bb7f69ed1a5df5231a43c3137"

PV="2.0.0"
PR = "r0"

S = "${WORKDIR}/git/ti/runtime/mmap"