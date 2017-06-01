inherit ti-pdk

require qmss-lld.inc

PR = "${INC_PR}.0"

DEPENDS_append = " rm-lld-rtos"
