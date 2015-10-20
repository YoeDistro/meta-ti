LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/ti/drv/cppi/COPYING.txt;md5=e8f6789acdcda76d02ed9203fc2e603d"

BRANCH = "master"
SRC_URI = "git://git.ti.com/keystone-rtos/cppi-lld.git;destsuffix=git/ti/drv/cppi;protocol=git;branch=${BRANCH}"

# Below Commit ID corresponds to "DEV.CPPI_LLD.02.01.00.10"
SRCREV = "97c537fa544a78e83512db3499a49d0bd86fad32"
PV = "02.01.00.10"

DEVICELIST = "k2h k2k k2l k2e"

BASEDIR = "${WORKDIR}/git"
S = "${BASEDIR}/ti/drv/cppi"
