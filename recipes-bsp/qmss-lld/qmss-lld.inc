LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/ti/drv/qmss/COPYING.txt;md5=e8f6789acdcda76d02ed9203fc2e603d"

BRANCH ="master"
SRC_URI = "git://git.ti.com/keystone-rtos/qmss-lld.git;destsuffix=git/ti/drv/qmss;protocol=git;branch=${BRANCH}"

# commit ID corresponding to DEV.QMSS_LLD.02.01.00.10
SRCREV = "ffec14248f460b7cf4def4457f9c2335df62618d"
PV = "02.01.00.10"
PR = "r1"

DEVICELIST = "k2h k2k k2l k2e"

BASEDIR = "${WORKDIR}/git"
S = "${BASEDIR}/ti/drv/qmss"
