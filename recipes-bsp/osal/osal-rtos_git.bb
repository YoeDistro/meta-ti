inherit ti-pdk
require recipes-bsp/osal/osal.inc

PR = "${INC_PR}.0"

COMPATIBLE_MACHINE_append = "|c66x|k3"

DEPENDS_append_ti33x = " starterware-rtos"
DEPENDS_append_ti43x = " starterware-rtos"

# Build with make instead of XDC
TI_PDK_XDCMAKE = "0"

export PDK_OSAL_ROOT_PATH ="${WORKDIR}/build"
export DEST_ROOT="${S}"

# HTML doc link params
PDK_COMP_LINK_TEXT = "OSAL Library"
