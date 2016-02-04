LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/COPYING.txt;md5=f2b4f162358b1ffaf3f2307287ca2074"

COMPATIBLE_MACHINE = "keystone"
PACKAGE_ARCH = "${MACHINE_ARCH}"

DEBIAN_NOAUTONAME_${PN} = "1"
DEBIAN_NOAUTONAME_${PN}-dev = "1"
DEBIAN_NOAUTONAME_${PN}-dbg = "1"
DEBIAN_NOAUTONAME_${PN}-staticdev = "1"

BRANCH = "master"
SRC_URI = "git://git.ti.com/keystone-rtos/netapi.git;protocol=git;branch=${BRANCH}"

# Below Commit ID corresponds to "DEV.NETAPI.01.01.00.06"
SRCREV = "8180b61615965954eaaedc26053f9a8939d9a273"
PV = "01.01.00.06"
INC_PR = "r4"

DEVICELIST_k2hk-evm = "k2h k2k"
DEVICELIST_k2l-evm  = "k2l"
DEVICELIST_k2e-evm  = "k2e"

CHOICELIST = "yes no"

BASEDIR = "${WORKDIR}/git"
S = "${BASEDIR}/ti/runtime/netapi"
