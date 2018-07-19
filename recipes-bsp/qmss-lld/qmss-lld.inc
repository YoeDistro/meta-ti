LICENSE = "BSD-3-Clause & TI-TFL"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/ti/drv/qmss/COPYING.txt;md5=cd04fb087063a2a82c4ce61983836871"

COMPATIBLE_MACHINE = "keystone"
PACKAGE_ARCH = "${MACHINE_ARCH}"

QMSS_LLD_GIT_URI = "git://git.ti.com/keystone-rtos/qmss-lld.git"
QMSS_LLD_GIT_PROTOCOL = "git"
QMSS_LLD_GIT_BRANCH = "master"
QMSS_LLD_GIT_DESTSUFFIX = "git/ti/drv/qmss"

# Below commit ID corresponding to "DEV.QMSS_LLD.02.01.00.16"
QMSS_LLD_SRCREV = "7934ae59f545b302ede96482e61a1f79ff3518ce"

BRANCH ="${QMSS_LLD_GIT_BRANCH}"
SRC_URI = "${QMSS_LLD_GIT_URI};destsuffix=${QMSS_LLD_GIT_DESTSUFFIX};protocol=${QMSS_LLD_GIT_PROTOCOL};branch=${BRANCH}"
SRCREV = "${QMSS_LLD_SRCREV}"

PV = "02.01.00.16"
INC_PR = "r0"

DEVICELIST = "k2h k2k k2l k2e"
DEVICELIST_k2hk = "k2h k2k"
DEVICELIST_k2l = "k2l"
DEVICELIST_k2e = "k2e"

S = "${WORKDIR}/${QMSS_LLD_GIT_DESTSUFFIX}"
