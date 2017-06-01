inherit ti-pdk
require cppi-lld.inc

PR = "${INC_PR}.0"

DEPENDS_append = " rm-lld-rtos \
                   qmss-lld-rtos \
"
