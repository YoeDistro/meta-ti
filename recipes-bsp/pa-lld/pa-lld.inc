LICENSE = "BSD-3-Clause & TI-TFL"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/ti/drv/pa/COPYING.txt;md5=cd04fb087063a2a82c4ce61983836871"

COMPATIBLE_MACHINE = "keystone"
PACKAGE_ARCH = "${MACHINE_ARCH}"

PA_LLD_GIT_URI = "git://git.ti.com/keystone-rtos/pa-lld.git"
PA_LLD_GIT_PROTOCOL = "git"
PA_LLD_GIT_BRANCH = "master"
PA_LLD_GIT_DESTSUFFIX = "git/ti/drv/pa"

# Below Commit ID corresponds to "DEV.PA_LLD.03.00.02.01"
PA_LLD_SRCREV = "e95de49323678a55072c90fb213b94f96cf494b1"

BRANCH = "${PA_LLD_GIT_BRANCH}"
SRC_URI = "${PA_LLD_GIT_URI};destsuffix=${PA_LLD_GIT_DESTSUFFIX};protocol=${PA_LLD_GIT_PROTOCOL};branch=${BRANCH}"
SRCREV = "${PA_LLD_SRCREV}"

PV = "03.00.02.01"
INC_PR = "r0"

S = "${WORKDIR}/${PA_LLD_GIT_DESTSUFFIX}"

DEVICELIST = "k2h k2k k2l k2e"
DEVICELIST_k2hk-evm = "k2h k2k"
DEVICELIST_k2l-evm = "k2l"
DEVICELIST_k2e = "k2e"

LIBPA = "libpa"
LIBPA_k2hk-evm = "libpa"
LIBPA_k2e = "libpa2"
LIBPA_k2l-evm = "libpa2"
