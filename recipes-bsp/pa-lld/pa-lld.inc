LICENSE = "BSD-3-Clause & TI-TFL"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/ti/drv/pa/COPYING.txt;md5=cd04fb087063a2a82c4ce61983836871"

COMPATIBLE_MACHINE = "k2hk|k2e|k2l"
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit ti-pdk-fetch

TI_PDK_COMP = "ti.drv.pa"

PE = "1"

INC_PR = "r0"


DEVICELIST = "k2h k2k k2l k2e"
DEVICELIST_k2hk = "k2h k2k"
DEVICELIST_k2l = "k2l"
DEVICELIST_k2e = "k2e"

LIBPA = "libpa"
LIBPA_k2hk = "libpa"
LIBPA_k2e = "libpa2"
LIBPA_k2l = "libpa2"
