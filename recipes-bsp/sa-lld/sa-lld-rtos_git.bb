SUMMARY = "TI RTOS low level driver for Security Accelerator (SA)"

inherit ti-pdk

require recipes-bsp/sa-lld/sa-lld.inc

PR = "${INC_PR}.0"

COMPATIBLE_MACHINE_append = "|c667x-evm"

PACKAGE_ARCH = "${MACHINE_ARCH}"

# HTML doc link params
PDK_COMP_LINK_TEXT = "SA LLD"
