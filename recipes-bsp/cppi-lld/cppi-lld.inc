LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/ti/drv/cppi/COPYING.txt;md5=e8f6789acdcda76d02ed9203fc2e603d"

BRANCH = "master"
SRC_URI = "git://git.ti.com/keystone-rtos/cppi-lld.git;destsuffix=git/ti/drv/cppi;protocol=git;branch=${BRANCH}"

# Below Commit ID corresponds to "DEV.CPPI_LLD.02.01.00.06"
SRCREV = "4c6c0a857786c49d8003453d3bc7c3c97ff4f5a4"
PV = "02.01.00.06"
PR = "r0"

DEVICELIST = "k2h k2k k2l k2e"

BASEDIR = "${WORKDIR}/git"
S = "${BASEDIR}/ti/drv/cppi"
