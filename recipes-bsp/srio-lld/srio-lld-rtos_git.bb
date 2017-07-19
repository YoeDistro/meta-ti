SUMMARY = "TI RTOS low level driver for Serial Rapid IO (SRIO)"

inherit ti-pdk

require recipes-bsp/srio-lld/srio-lld.inc

PR = "${INC_PR}.0"

DEPENDS_append = " cppi-lld-rtos \
                   qmss-lld-rtos \
"
COMPATIBLE_MACHINE_append = "|c66x"

PACKAGE_ARCH = "${MACHINE_ARCH}"

# HTML doc link params
PDK_COMP_LINK_TEXT = "SRIO LLD"
