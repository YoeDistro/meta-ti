SUMMARY = "TI RTOS low level driver for Antenna Interface"

inherit ti-pdk

require recipes-bsp/aif2-lld/aif2-lld.inc

PR = "${INC_PR}.0"

DEPENDS_append = " qmss-lld-rtos \
                   cppi-lld-rtos \
"

PACKAGE_ARCH = "${MACHINE_ARCH}"

# HTML doc link params
PDK_COMP_LINK_TEXT = "AIF2 LLD"
