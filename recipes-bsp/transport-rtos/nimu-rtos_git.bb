SUMMARY = "RTOS driver for Network Interface Management Unit (NIMU)"

inherit ti-pdk
require transport.inc

PR = "${INC_PR}.0"

LIC_FILES_CHKSUM = "file://nimu_eth.h;beginline=1;endline=35;md5=ee9c662c39d4584fb2a8b66413d2866d"

COMPATIBLE_MACHINE = "ti33x|ti43x|omap-a15|keystone|c66x|omapl1|k3"

TI_PDK_COMP = "ti.transport.ndk.nimu"

DEPENDS_append = " ti-ndk osal-rtos"
DEPENDS_append_ti33x = " starterware-rtos emac-lld-rtos"
DEPENDS_append_ti43x = " starterware-rtos emac-lld-rtos"
DEPENDS_append_omap-a15 = " emac-lld-rtos"
DEPENDS_append_keystone = " qmss-lld-rtos cppi-lld-rtos"
DEPENDS_append_k2hk = " pa-lld-rtos"
DEPENDS_append_k2e = " pa-lld-rtos"
DEPENDS_append_k2l = " pa-lld-rtos"
DEPENDS_append_k2g = " emac-lld-rtos"
DEPENDS_append_c665x-evm = " emac-lld-rtos"
DEPENDS_append_c667x-evm = " pa-lld-rtos cppi-lld-rtos"
DEPENDS_append_omapl1 = " emac-lld-rtos"
DEPENDS_append_k3 = " emac-lld-rtos pruss-lld-rtos"

# Build with make instead of XDC
TI_PDK_XDCMAKE = "0"

export PDK_NIMU_ROOT_PATH ="${WORKDIR}/build"
export DEST_ROOT="${S}"

export NDK_INSTALL_PATH = "${NDK_INSTALL_DIR}"
XDCPATH_append = ";${NDK_INSTALL_DIR}/packages"
