SUMMARY = "TI RTOS low level driver for IQN2 peripheral module"

inherit ti-pdk

require recipes-bsp/iqn2-lld/iqn2-lld.inc

PR = "${INC_PR}.0"

PACKAGE_ARCH = "${MACHINE_ARCH}"

# HTML doc link params
PDK_COMP_LINK_TEXT = "IQN2 LLD"
