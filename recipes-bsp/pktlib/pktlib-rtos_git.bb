inherit ti-pdk
require pktlib.inc

PR = "${INC_PR}.0"

DEPENDS_append = " rm-lld-rtos \
                   qmss-lld-rtos \
                   cppi-lld-rtos \
"

TI_PDK_LIMIT_SOCS_k2hk = "k2h k2k"
TI_PDK_LIMIT_SOCS_k2e = "k2e"
TI_PDK_LIMIT_SOCS_k2l-evm = "k2l"
