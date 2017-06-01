inherit ti-pdk
require pktlib.inc

PR = "${INC_PR}.0"

DEPENDS_append = " rm-lld-rtos \
                   qmss-lld-rtos \
                   cppi-lld-rtos \
"
