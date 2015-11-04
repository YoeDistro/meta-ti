LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/ti/drv/qmss/COPYING.txt;md5=e8f6789acdcda76d02ed9203fc2e603d"

QMSS_LLD_GIT_URI = "git://git.ti.com/keystone-rtos/qmss-lld.git"
QMSS_LLD_GIT_PROTOCOL = "git"
QMSS_LLD_GIT_BRANCH = "master"
QMSS_LLD_GIT_DESTSUFFIX = "git/ti/drv/qmss"

# commit ID corresponding to DEV.QMSS_LLD.02.01.00.11
QMSS_LLD_SRCREV = "2024ab2d411c1a6dd3f5e768a3cb30c2a685f1e4"

BRANCH ="${QMSS_LLD_GIT_BRANCH}"
SRC_URI = "${QMSS_LLD_GIT_URI};destsuffix=${QMSS_LLD_GIT_DESTSUFFIX};protocol=${QMSS_LLD_GIT_PROTOCOL};branch=${BRANCH}"
SRCREV = "${QMSS_LLD_SRCREV}"

PV = "02.01.00.11"
INC_PR = "r1"

DEVICELIST = "k2h k2k k2l k2e"

S = "${WORKDIR}/${QMSS_LLD_GIT_DESTSUFFIX}"
