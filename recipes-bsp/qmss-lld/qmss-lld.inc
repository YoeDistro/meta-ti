LICENSE = "BSD-3-Clause & TI-TFL"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/ti/drv/qmss/COPYING.txt;md5=cd04fb087063a2a82c4ce61983836871"

COMPATIBLE_MACHINE = "keystone"
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit ti-pdk-fetch

TI_PDK_COMP = "ti.drv.qmss"

PE = "1"

INC_PR = "r0"

DEVICELIST = "k2h k2k k2l k2e"
DEVICELIST:k2hk = "k2h k2k"
DEVICELIST:k2l = "k2l"
DEVICELIST:k2e = "k2e"
DEVICELIST:k2g = "k2h"
