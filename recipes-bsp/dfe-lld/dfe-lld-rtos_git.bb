SUMMARY = "TI RTOS level driver for Digital Radio Front End"

inherit ti-pdk

require recipes-bsp/dfe-lld/dfe-lld.inc

PR = "${INC_PR}.0"

PACKAGE_ARCH = "${MACHINE_ARCH}"

# HTML doc link params
PDK_COMP_LINK_TEXT = "DFE LLD"
