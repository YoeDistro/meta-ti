SUMMARY = "TI RTOS low level driver for ICSS-EMAC"

inherit ti-pdk
require icss-emac-lld.inc

PR = "${INC_PR}.0"

DEPENDS:append = " osal-rtos \
                   pruss-lld-rtos \
"
DEPENDS:append:ti33x = " starterware-rtos \
"
DEPENDS:append:ti43x = " starterware-rtos \
"

# Build with make instead of XDC
TI_PDK_XDCMAKE = "0"

export PDK_ICSS_EMAC_ROOT_PATH ="${WORKDIR}/build"
export DEST_ROOT="${S}"

# HTML doc link params
PDK_COMP_LINK_TEXT = "ICSS-EMAC LLD"

INSANE_SKIP:${PN} = "arch"
