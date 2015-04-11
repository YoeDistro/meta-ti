LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/COPYING.txt;md5=f2b4f162358b1ffaf3f2307287ca2074"

BRANCH = "master"
SRC_URI = "git://git.ti.com/keystone-rtos/netapi.git;protocol=git;branch=${BRANCH}"

# Below Commit ID corresponds to "DEV.NETAPI.01.01.00.05C"
SRCREV = "fc407a408e6c7e4cad6b5f0dd4367049abfd375f"
PV = "01.01.00.05"

DEVICELIST = "k2h k2k k2l k2e"

CHOICELIST = "yes no"

BASEDIR = "${WORKDIR}/git"
S = "${BASEDIR}/ti/runtime/netapi"
