LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/ti/drv/cppi/COPYING.txt;md5=e8f6789acdcda76d02ed9203fc2e603d"

COMPATIBLE_MACHINE = "keystone"
PACKAGE_ARCH = "${MACHINE_ARCH}"

CPPI_LLD_GIT_URI = "git://git.ti.com/keystone-rtos/cppi-lld.git"
CPPI_LLD_GIT_PROTOCOL = "git"
CPPI_LLD_GIT_BRANCH = "master"
CPPI_LLD_GIT_DESTSUFFIX = "git/ti/drv/cppi"

# Below Commit ID corresponds to "DEV.CPPI_LLD.02.01.00.13"
CPPI_LLD_SRCREV = "e9c498fe581b502415123a8e3dd7c0a08be03958"

BRANCH = "${CPPI_LLD_GIT_BRANCH}"
SRC_URI = "${CPPI_LLD_GIT_URI};destsuffix=${CPPI_LLD_GIT_DESTSUFFIX};protocol=${CPPI_LLD_GIT_PROTOCOL};branch=${BRANCH}"

SRCREV = "${CPPI_LLD_SRCREV}"
PV = "02.01.00.13"
INC_PR = "r0"

DEVICELIST = "k2h k2k k2l k2e"
DEVICELIST_k2hk = "k2h k2k"
DEVICELIST_k2l  = "k2l"
DEVICELIST_k2e  = "k2e"

S = "${WORKDIR}/${CPPI_LLD_GIT_DESTSUFFIX}"
