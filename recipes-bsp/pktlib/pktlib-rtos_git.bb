inherit ti-pdk
require pktlib.inc

PR = "${INC_PR}.1"

DEPENDS_append = " rm-lld-rtos \
                   qmss-lld-rtos \
                   cppi-lld-rtos \
"
COMPATIBLE_MACHINE_append = "|c66x"
