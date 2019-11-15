LICENSE = "BSD-3-Clause"

LIC_FILES_CHKSUM = "file://COPYING.txt;md5=76cafd87d8495b42c2355f7681de15a2"
COMPATIBLE_MACHINE = "ti33x|ti43x|omap-a15|k2g"
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit ti-pdk-fetch

TI_PDK_COMP = "ti.drv.icss_emac"

PE = "1"

INC_PR = "r0"

