inherit ti-pdk
require pktlib.inc

PR = "${INC_PR}.1"

DEPENDS:append = " rm-lld-rtos \
                   qmss-lld-rtos \
                   cppi-lld-rtos \
"
COMPATIBLE_MACHINE:append = "|c66x"
