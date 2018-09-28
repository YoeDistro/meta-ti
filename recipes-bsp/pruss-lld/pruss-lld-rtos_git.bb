SUMMARY = "TI RTOS low level driver library for PRUSS"

inherit ti-pdk
require recipes-bsp/pruss-lld/pruss-lld.inc

COMPATIBLE_MACHINE_append = "|k3"
PR = "${INC_PR}.0"

S = "${WORKDIR}/${PRUSS_LLD_GIT_DESTSUFFIX}"

DEPENDS_append = " osal-rtos"
DEPENDS_append_ti33x = " starterware-rtos"
DEPENDS_append_ti43x = " starterware-rtos"

# Build with make instead of XDC
TI_PDK_XDCMAKE = "0"

export PDK_PRUSS_ROOT_PATH ="${WORKDIR}/build"
export DEST_ROOT="${WORKDIR}/build"

# HTML doc link params
PDK_COMP_LINK_TEXT = "PRUSS LLD"

INSANE_SKIP_${PN} = "arch"
