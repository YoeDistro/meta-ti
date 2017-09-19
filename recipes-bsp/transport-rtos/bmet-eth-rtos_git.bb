SUMMARY = "RTOS driver for BMET Ethernet"

inherit ti-pdk
require transport.inc

PR = "${INC_PR}.0"

LIC_FILES_CHKSUM = "file://bmet_transport.h;beginline=12;endline=40;md5=7c7fbe6af03d79b61025a67a8fc96f93"

COMPATIBLE_MACHINE = "k2hk-evm|k2e-evm|k2l-evm"

DEPENDS_append = " qmss-lld-rtos \
                   cppi-lld-rtos \
"

BMET_ETH_DESTSUFFIX = "git/bmet_eth"

S = "${WORKDIR}/${BMET_ETH_DESTSUFFIX}"

# HTML doc link params
PDK_COMP_LINK_TEXT = "Baremetal Ethernet Transport"
