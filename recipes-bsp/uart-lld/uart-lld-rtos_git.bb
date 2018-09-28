SUMMARY = "TI RTOS low level driver for UART"
DESCRIPTION = "TI RTOS low level driver for Universal Asynchronous Receiver/Transmitter (UART) module "

inherit ti-pdk

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://UART.h;beginline=1;endline=31;md5=8580f9c5c0de8d5d13518cf18a0122b8"

COMPATIBLE_MACHINE = "ti33x|ti43x|omap-a15|keystone|omapl1|c66x|k3"
PACKAGE_ARCH = "${MACHINE_ARCH}"

UART_LLD_GIT_URI = "git://git.ti.com/keystone-rtos/uart-lld.git"
UART_LLD_GIT_PROTOCOL = "git"
UART_LLD_GIT_BRANCH = "master"

# Below commit ID corresponds to "DEV.UART_LLD.01.00.00.12"
UART_LLD_SRCREV = "224f9d87ed06ca4392744d394d0adaad6d2c392c"

BRANCH = "${UART_LLD_GIT_BRANCH}"
SRC_URI = "${UART_LLD_GIT_URI};protocol=${UART_LLD_GIT_PROTOCOL};branch=${BRANCH}"

SRCREV = "${UART_LLD_SRCREV}"
PV = "01.00.00.12"
PR = "r0"

DEPENDS_append = " edma3-lld-rtos \
                   osal-rtos \
"
DEPENDS_append_ti33x = " starterware-rtos"
DEPENDS_append_ti43x = " starterware-rtos"

DEPENDS_remove_k3 = "edma3-lld-rtos "
DEPENDS_append_k3 = " udma-lld-rtos "

# Build with make instead of XDC
TI_PDK_XDCMAKE = "0"

export PDK_UART_ROOT_PATH ="${WORKDIR}/build"
export DEST_ROOT="${S}"

export EDMA3LLD_BIOS6_INSTALLDIR = "${EDMA3_LLD_INSTALL_DIR}"
XDCPATH_append = ";${EDMA3_LLD_INSTALL_DIR}/packages"

# HTML doc link params
PDK_COMP_LINK_TEXT = "UART LLD"
