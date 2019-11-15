SUMMARY = "TI RTOS low level driver for Telecom Serial Interface Port (TSIP)"

inherit ti-pdk ti-pdk-fetch

TI_PDK_COMP = "ti.drv.tsip"

PE = "1"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://tsip.h;beginline=1;endline=32;md5=dab2257b0b8a3678c26915f6eb49b71e"

COMPATIBLE_MACHINE = "k2e|c667x-evm"
PACKAGE_ARCH = "${MACHINE_ARCH}"

PR = "r0"

# HTML doc link params
PDK_COMP_LINK_TEXT = "TSIP LLD"
