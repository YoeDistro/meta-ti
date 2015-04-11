LICENSE = "BSD-3-Clause"

COMPATIBLE_MACHINE = "keystone"

LIC_FILES_CHKSUM = "file://COPYING.txt;md5=b3315b8190036fe1c6a8263d02e15606"

BRANCH="master"
SRC_URI = "git://git.ti.com/keystone-rtos/mmap-lld.git;destsuffix=git/ti/runtime/mmap;protocol=git;branch=${BRANCH}"
# Commit correspond to DEV.MMAP.02.00.00.04
SRCREV = "306895eb2d5decd14a760e70d273946958ff716f"

PV = "2.0.0.4"
PR = "r1"

S = "${WORKDIR}/git/ti/runtime/mmap"
