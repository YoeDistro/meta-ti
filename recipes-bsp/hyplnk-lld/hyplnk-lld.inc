DESCRIPTION = "TI Hyperlink Low Level Driver"
LICENSE = "BSD-3-Clause"

COMPATIBLE_MACHINE = "k2hk|k2e"
PACKAGE_ARCH = "${MACHINE_ARCH}"

LLDNAME="hyplnk"

LIC_FILES_CHKSUM = "file://COPYING.txt;md5=623325cc19e613a4e770fbb749922592"

inherit ti-pdk-fetch

TI_PDK_COMP = "ti.drv.hyplnk"

PE = "1"

INC_PR = "r0"

DEVICELIST_k2hk = "k2h k2k"
DEVICELIST_k2e  = "k2e"

