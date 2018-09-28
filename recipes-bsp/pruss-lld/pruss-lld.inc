LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://pruicss.h;beginline=1;endline=31;md5=315177aa03d58bcd124a6e799e2bd458"

COMPATIBLE_MACHINE = "ti33x|ti43x|omap-a15|k2g"
PACKAGE_ARCH = "${MACHINE_ARCH}"

PRUSS_LLD_GIT_URI = "git://git.ti.com/keystone-rtos/pruss-lld.git"
PRUSS_LLD_GIT_PROTOCOL = "git"
PRUSS_LLD_GIT_BRANCH = "master"
PRUSS_LLD_GIT_DESTSUFFIX = "git/ti/drv/pruss"

# Below commit ID corresponds to "DEV.PRUSS_LLD.01.00.00.11"
PRUSS_LLD_SRCREV = "4319eb250685f672979d4d6cc80c40a56e1afa0d"

BRANCH = "${PRUSS_LLD_GIT_BRANCH}"
SRC_URI = "${PRUSS_LLD_GIT_URI};destsuffix=${PRUSS_LLD_GIT_DESTSUFFIX};protocol=${PRUSS_LLD_GIT_PROTOCOL};branch=${BRANCH}"

SRCREV = "${PRUSS_LLD_SRCREV}"
PV = "01.00.00.11"
INC_PR = "r0"

