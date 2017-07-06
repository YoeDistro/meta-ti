inherit ti-pdk

require qmss-lld.inc

PR = "${INC_PR}.1"

COMPATIBLE_MACHINE_append = "|c66x"

DEPENDS_append = " rm-lld-rtos"
