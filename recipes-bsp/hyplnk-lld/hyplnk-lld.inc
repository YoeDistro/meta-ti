DESCRIPTION = "TI Hyperlink Low Level Driver"
LICENSE = "BSD-3-Clause"

COMPATIBLE_MACHINE = "k2hk-evm|k2e-evm"
PACKAGE_ARCH = "${MACHINE_ARCH}"

LLDNAME="hyplnk"

LIC_FILES_CHKSUM = "file://${WORKDIR}/${HYPLNK_GIT_DESTSUFFIX}/COPYING.txt;md5=623325cc19e613a4e770fbb749922592"

HYPLNK_GIT_URI = "git://git.ti.com/keystone-rtos/hyplnk-lld.git"
HYPLNK_GIT_PROTOCOL = "git"
HYPLNK_GIT_BRANCH = "master"
HYPLNK_GIT_DESTSUFFIX = "git/ti/drv/${LLDNAME}"

# Following commit corresponds to tag DEV.HYPLNK_LLD.02.01.00.06
HYPLNK_SRCREV = "a1953ca33114c5a4daca6289a7fb75b201f566d2"

BRANCH="${HYPLNK_GIT_BRANCH}"
SRC_URI = "${HYPLNK_GIT_URI};destsuffix=${HYPLNK_GIT_DESTSUFFIX};protocol=${HYPLNK_GIT_PROTOCOL};branch=${BRANCH}"
SRCREV = "${HYPLNK_SRCREV}"

PV = "2.1.0.6"
INC_PR = "r0"

DEVICELIST_k2hk-evm = "k2h k2k"
DEVICELIST_k2e-evm  = "k2e"

S = "${WORKDIR}/${HYPLNK_GIT_DESTSUFFIX}"
