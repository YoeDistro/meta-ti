SUMMARY = "TI Bit Coprocessor (BCP) low level driver"

inherit ti-pdk ti-pdk-fetch

TI_PDK_COMP = "ti.drv.bcp"

PE = "1"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://bcp.h;beginline=1;endline=40;md5=4f6e6128dd075a89548c0287a39b8896"

COMPATIBLE_MACHINE = "k2hk|k2l|c667x-evm"
PACKAGE_ARCH = "${MACHINE_ARCH}"

PR = "r0"


DEPENDS_append = " qmss-lld-rtos \
                   cppi-lld-rtos \
"

# HTML doc link params
PDK_COMP_LINK_TEXT = "BCP LLD"
