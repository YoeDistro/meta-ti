LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/ti/drv/qmss/COPYING.txt;md5=e8f6789acdcda76d02ed9203fc2e603d"

BRANCH ="master"
SRC_URI = "git://git.ti.com/keystone-rtos/qmss-lld.git;destsuffix=git/ti/drv/qmss;protocol=git;branch=${BRANCH}"

# commit ID corresponding to DEV.QMSS_LLD.02.01.00.06
SRCREV = "661fff76530fd56cf7550f76c7b7a541ea18fda8"
PV = "02.01.00.06"
PR = "r0"

DEVICELIST = "k2h k2k k2l k2e"

BASEDIR = "${WORKDIR}/git"
S = "${BASEDIR}/ti/drv/qmss"
