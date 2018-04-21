SUMMARY = "TI Network abstraction layer RTOS LLD library "

inherit ti-pdk

require recipes-bsp/nwal-lld/nwal-lld.inc

PR = "${INC_PR}.0"

DEPENDS_append = " cppi-lld-rtos \
                   pa-lld-rtos \
                   pktlib-rtos \
                   qmss-lld-rtos \
                   sa-lld-rtos \
"

COMPATIBLE_MACHINE = "k2hk|k2e|k2l|c667x-evm"

# HTML doc link params
PDK_COMP_LINK_TEXT = "NWAL LLD"
