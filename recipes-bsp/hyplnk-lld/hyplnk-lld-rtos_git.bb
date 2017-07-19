SUMMARY = "TI RTOS low level driver for Hyperlink peripheral"

inherit ti-pdk

require recipes-bsp/hyplnk-lld/hyplnk-lld.inc

PR = "${INC_PR}.0"

COMPATIBLE_MACHINE_append = "|c66x"

# HTML doc link params
PDK_COMP_LINK_TEXT = "HYPLNK LLD"
