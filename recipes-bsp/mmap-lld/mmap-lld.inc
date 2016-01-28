LICENSE = "BSD-3-Clause"

COMPATIBLE_MACHINE = "keystone"

LIC_FILES_CHKSUM = "file://COPYING.txt;md5=b3315b8190036fe1c6a8263d02e15606"

MMAP_LLD_GIT_URI = "git://git.ti.com/keystone-rtos/mmap-lld.git"
MMAP_LLD_GIT_PROTOCOL = "git"
MMAP_LLD_GIT_BRANCH = "master"
MMAP_LLD_GIT_DESTSUFFIX = "git/ti/runtime/mmap"

# Commit correspond to DEV.MMAP.02.00.00.06
MMAP_LLD_SRCREV = "70c10fbce809a3ed1cd67c9da2e2a47d3de8f832"

BRANCH="${MMAP_LLD_GIT_BRANCH}"
SRC_URI = "${MMAP_LLD_GIT_URI};destsuffix=${MMAP_LLD_GIT_DESTSUFFIX};protocol=${MMAP_LLD_GIT_PROTOCOL};branch=${BRANCH}"
SRCREV = "${MMAP_LLD_SRCREV}"

PV = "2.0.0.6"
INC_PR = "r0"

S = "${WORKDIR}/git/ti/runtime/mmap"
