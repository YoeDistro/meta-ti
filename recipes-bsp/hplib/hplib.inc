COMPATIBLE_MACHINE = "keystone"

DEVICELIST = "k2h k2k k2l k2e"

BRANCH = "master"
SRC_URI = "git://git.ti.com/keystone-rtos/hplib.git;protocol=git;branch=${BRANCH}"

# Below Commit ID corresponds to "DEV.HPLIB.01.01.00.06"
SRCREV = "1cbe6a41926c131d9cab63b40d43807c6e56e2fd"
PV = "01.01.00.06+git${SRCPV}"
