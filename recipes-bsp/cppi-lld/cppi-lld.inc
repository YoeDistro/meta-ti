LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/ti/drv/cppi/COPYING.txt;md5=e8f6789acdcda76d02ed9203fc2e603d"

BRANCH = "master"
SRC_URI = "git://git.ti.com/keystone-rtos/cppi-lld.git;destsuffix=git/ti/drv/cppi;protocol=git;branch=${BRANCH}"

# Below Commit ID corresponds to "DEV.CPPI_LLD.02.01.00.08"
SRCREV = "320af092dc382af52b9e68f14ca8acd5c1c467c1"
PV = "02.01.00.09"
PR = "r1"

DEVICELIST = "k2h k2k k2l k2e"

BASEDIR = "${WORKDIR}/git"
S = "${BASEDIR}/ti/drv/cppi"
