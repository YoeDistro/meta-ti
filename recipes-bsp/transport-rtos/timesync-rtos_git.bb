SUMMARY = "RTOS driver for timesync driver"

inherit ti-pdk
require recipes-bsp/transport-rtos/transport.inc

PR = "${INC_PR}.0"

LIC_FILES_CHKSUM = "file://timeSync_ver.h;beginline=23;endline=45;md5=4bd873035e47065aaa811e53622992e9"

COMPATIBLE_MACHINE = "ti33x|ti43x|omap-a15|k2g"

DEPENDS_append = " ti-ndk \
                   osal-rtos \
                   pruss-lld-rtos \
                   icss-emac-lld-rtos \
                   nimu-icss-rtos \
                   edma3-lld-rtos \
"

TI_PDK_COMP = "ti.transport.timeSync"

# Build with make instead of XDC
TI_PDK_XDCMAKE = "0"

export PDK_TIMESYNC_ROOT_PATH ="${WORKDIR}/build"
export DEST_ROOT="${S}"

export NDK_INSTALL_PATH = "${NDK_INSTALL_DIR}"
export EDMA3LLD_BIOS6_INSTALLDIR = "${EDMA3_LLD_INSTALL_DIR}"

XDCPATH_append = ";${NDK_INSTALL_DIR}/packages"
