LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/COPYING.txt;md5=b7982a377c680ad71ca2fbb735982462"

COMPATIBLE_MACHINE = "keystone"

BRANCH = "master"
SRC_URI = "git://git.ti.com/keystone-rtos/nwal-lld.git;protocol=git;branch=${BRANCH}"

# Below commit ID corresponds to "DEV.NWAL.02.01.00.05A"
SRCREV = "05c0222714791ad95672293196a33fffab041fc9"
PV = "02.01.00.05"

BASEDIR = "${WORKDIR}/git"
S = "${BASEDIR}/ti/drv/nwal"

DEVICELIST = "k2h k2k k2l k2e"

CHOICELIST = "yes no"
