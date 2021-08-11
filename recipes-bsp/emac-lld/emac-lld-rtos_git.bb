SUMMARY = "TI RTOS low level driver for Ethernet MAC (EMAC) peripheral"

inherit ti-pdk

require emac-lld.inc

PR = "r0"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://emacver.h;beginline=1;endline=47;md5=f66bb3695972ca2a85d990a390f45d88"

COMPATIBLE_MACHINE = "ti33x|ti43x|omap-a15|k2g|omapl1|c665x-evm|k3"
PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS:append = " board-rtos osal-rtos"
DEPENDS:append:ti33x = " starterware-rtos"
DEPENDS:append:ti43x = " starterware-rtos"
DEPENDS:append:k2g  = " cppi-lld-rtos qmss-lld-rtos"

DEPENDS:remove:k3 = "board-rtos "
DEPENDS:append:k3 = " udma-lld-rtos trace-rtos"

# Build with make instead of XDC
TI_PDK_XDCMAKE = "0"

export PDK_EMAC_ROOT_PATH ="${WORKDIR}/build"
export DEST_ROOT="${S}"

# HTML doc link params
PDK_COMP_LINK_TEXT = "EMAC LLD"
