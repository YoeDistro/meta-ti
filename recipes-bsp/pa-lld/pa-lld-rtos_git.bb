SUMMARY = "TI RTOS low level driver for Packet Accelerator (PA)"

inherit ti-pdk

require recipes-bsp/pa-lld/pa-lld.inc
COMPATIBLE_MACHINE_append = "|c667x-evm"
PR = "${INC_PR}.0"

DEPENDS_append = " rm-lld-rtos"

# HTML doc link params
PDK_COMP_LINK_TEXT = "PA LLD"
