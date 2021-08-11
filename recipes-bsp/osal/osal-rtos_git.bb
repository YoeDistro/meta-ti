inherit ti-pdk
require recipes-bsp/osal/osal.inc

PR = "${INC_PR}.0"

COMPATIBLE_MACHINE:append = "|c66x|k3"

DEPENDS:append:ti33x = " starterware-rtos"
DEPENDS:append:ti43x = " starterware-rtos"

# Build with make instead of XDC
TI_PDK_XDCMAKE = "0"

export PDK_OSAL_ROOT_PATH ="${WORKDIR}/build"
export DEST_ROOT="${S}"

# HTML doc link params
PDK_COMP_LINK_TEXT = "OSAL Library"
