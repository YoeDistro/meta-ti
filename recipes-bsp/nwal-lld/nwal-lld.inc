LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/COPYING.txt;md5=b7982a377c680ad71ca2fbb735982462"

COMPATIBLE_MACHINE = "keystone"
PACKAGE_ARCH = "${MACHINE_ARCH}"

NWAL_LLD_GIT_URI = "git://git.ti.com/keystone-rtos/nwal-lld.git"
NWAL_LLD_GIT_PROTOCOL = "git"
NWAL_LLD_GIT_BRANCH = "master"

# Below commit ID corresponds to "DEV.NWAL.02.01.00.10A"
NWAL_LLD_SRCREV = "e7ed8c5abc4b93b99a5f45699eef8f3b8ca51d1e"

BRANCH = "${NWAL_LLD_GIT_BRANCH}"
SRC_URI = "${NWAL_LLD_GIT_URI};protocol=${NWAL_LLD_GIT_PROTOCOL};branch=${BRANCH}"
SRCREV = "${NWAL_LLD_SRCREV}"

PV = "02.01.00.10"
INC_PR = "r1"

BASEDIR = "${WORKDIR}/git"
S = "${BASEDIR}/ti/drv/nwal"

DEVICELIST_k2hk-evm = "k2h k2k"
DEVICELIST_k2l-evm  = "k2l"
DEVICELIST_k2e-evm  = "k2e"

CHOICELIST = "yes no"
