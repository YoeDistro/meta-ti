inherit ti-pdk
require cppi-lld.inc

PR = "${INC_PR}.0"

DEPENDS_append = " rm-lld-rtos \
                   qmss-lld-rtos \
"

TI_PDK_LIMIT_SOCS_k2hk = "k2h k2k"
TI_PDK_LIMIT_SOCS_k2e = "k2e"
TI_PDK_LIMIT_SOCS_k2l-evm = "k2l"
TI_PDK_LIMIT_SOCS_k2g = "k2g"
