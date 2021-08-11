SUMMARY = "TI RTOS low level driver for UART"
DESCRIPTION = "TI RTOS low level driver for Universal Asynchronous Receiver/Transmitter (UART) module "

inherit ti-pdk ti-pdk-fetch

TI_PDK_COMP = "ti.drv.uart"

PE = "1"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING.txt;beginline=1;endline=31;md5=94b6a199da1caf777f6756cb70aca4a7"

COMPATIBLE_MACHINE = "ti33x|ti43x|omap-a15|keystone|omapl1|c66x|k3"
PACKAGE_ARCH = "${MACHINE_ARCH}"

PR = "r0"

DEPENDS:append = " edma3-lld-rtos \
                   osal-rtos \
"
DEPENDS:append:ti33x = " starterware-rtos \
                         pruss-lld-rtos \
"
DEPENDS:append:ti43x = " starterware-rtos"

DEPENDS:remove:k3 = "edma3-lld-rtos "
DEPENDS:append:k3 = " udma-lld-rtos "

# Build with make instead of XDC
TI_PDK_XDCMAKE = "0"

export PDK_UART_ROOT_PATH ="${WORKDIR}/build"
export DEST_ROOT="${S}"

export EDMA3LLD_BIOS6_INSTALLDIR = "${EDMA3_LLD_INSTALL_DIR}"
XDCPATH:append = ";${EDMA3_LLD_INSTALL_DIR}/packages"

# HTML doc link params
PDK_COMP_LINK_TEXT = "UART LLD"

INSANE_SKIP:${PN} = "arch"
