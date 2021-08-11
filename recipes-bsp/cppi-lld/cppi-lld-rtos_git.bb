inherit ti-pdk
require cppi-lld.inc

PR = "${INC_PR}.1"

COMPATIBLE_MACHINE:append = "|c66x"

DEPENDS:append = " rm-lld-rtos \
                   qmss-lld-rtos \
"
