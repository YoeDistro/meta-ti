LICENSE = "BSD-3-Clause & TI-TFL"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/ti/drv/qmss/COPYING.txt;md5=cd04fb087063a2a82c4ce61983836871"

COMPATIBLE_MACHINE = "keystone"
PACKAGE_ARCH = "${MACHINE_ARCH}"

QMSS_LLD_GIT_URI = "git://git.ti.com/keystone-rtos/qmss-lld.git"
QMSS_LLD_GIT_PROTOCOL = "git"
QMSS_LLD_GIT_BRANCH = "master"
QMSS_LLD_GIT_DESTSUFFIX = "git/ti/drv/qmss"

# commit ID corresponding to DEV.QMSS_LLD.02.01.00.14
QMSS_LLD_SRCREV = "4cc3a3f1bcddfc19e2e7d8dd4208ca215e7ecbae"

BRANCH ="${QMSS_LLD_GIT_BRANCH}"
SRC_URI = "${QMSS_LLD_GIT_URI};destsuffix=${QMSS_LLD_GIT_DESTSUFFIX};protocol=${QMSS_LLD_GIT_PROTOCOL};branch=${BRANCH}"
SRCREV = "${QMSS_LLD_SRCREV}"

PV = "02.01.00.14"
INC_PR = "r0"

DEVICELIST = "k2h k2k k2l k2e"
DEVICELIST_k2hk-evm = "k2h k2k"
DEVICELIST_k2l-evm = "k2l"
DEVICELIST_k2e-evm = "k2e"

S = "${WORKDIR}/${QMSS_LLD_GIT_DESTSUFFIX}"
