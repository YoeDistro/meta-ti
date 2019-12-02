SUMMARY = "RTOS driver for BMET Ethernet"

inherit ti-pdk
require transport.inc

PR = "${INC_PR}.0"

LIC_FILES_CHKSUM = "file://bmet_transport.h;beginline=12;endline=40;md5=6d209c698b273aef180b0c1d38469e8e"

COMPATIBLE_MACHINE = "k2hk|k2e|k2l"

DEPENDS_append = " qmss-lld-rtos \
                   cppi-lld-rtos \
"

TI_PDK_COMP = "ti.transport.bmet_eth"

# HTML doc link params
PDK_COMP_LINK_TEXT = "Baremetal Ethernet Transport"
