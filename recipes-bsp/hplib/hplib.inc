COMPATIBLE_MACHINE = "keystone"
PACKAGE_ARCH = "${MACHINE_ARCH}"

DEVICELIST_k2hk = "k2h k2k"
DEVICELIST_k2l  = "k2l"
DEVICELIST_k2e  = "k2e"

HPLIB_GIT_URI = "git://git.ti.com/git/keystone-rtos/hplib.git"
HPLIB_GIT_PROTOCOL = "https"
HPLIB_GIT_BRANCH = "master"

# Below Commit ID corresponds to "DEV.HPLIB.01.01.00.08"
HPLIB_SRCREV = "5e9b27854f5d6f9f37a0ed8d7516d4874427cd8b"

BRANCH = "${HPLIB_GIT_BRANCH}"
SRC_URI = "${HPLIB_GIT_URI};protocol=${HPLIB_GIT_PROTOCOL};branch=${BRANCH}"
SRCREV = "${HPLIB_SRCREV}"

PV = "01.01.00.08+git${SRCPV}"
INC_PR = "r0"
