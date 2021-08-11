inherit ti-pdk

require qmss-lld.inc

PR = "${INC_PR}.1"

COMPATIBLE_MACHINE:append = "|c66x"

DEPENDS:append = " rm-lld-rtos"
