SUMMARY = "TI RTOS low level driver for Serial Peripheral Interface (SPI)"

inherit ti-pdk ti-pdk-fetch

TI_PDK_COMP = "ti.drv.spi"

PE = "1"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://SPI.h;beginline=1;endline=31;md5=8580f9c5c0de8d5d13518cf18a0122b8"

COMPATIBLE_MACHINE = "ti33x|ti43x|omap-a15|keystone|c66x|omapl1|k3"
PACKAGE_ARCH = "${MACHINE_ARCH}"

PR = "r0"

DEPENDS_append = " osal-rtos \
                   edma3-lld-rtos \
"
DEPENDS_append_ti33x = " starterware-rtos pruss-lld-rtos gpio-lld-rtos"
DEPENDS_append_ti43x = " starterware-rtos pruss-lld-rtos gpio-lld-rtos"

DEPENDS_remove_k3 = "edma3-lld-rtos "
DEPENDS_append_k3 = " udma-lld-rtos"


# Build with make instead of XDC
TI_PDK_XDCMAKE = "0"

export PDK_SPI_ROOT_PATH ="${WORKDIR}/build"
export DEST_ROOT="${S}"

export EDMA3LLD_BIOS6_INSTALLDIR = "${EDMA3_LLD_INSTALL_DIR}"
XDCPATH_append = ";${EDMA3_LLD_INSTALL_DIR}/packages"

# HTML doc link params
PDK_COMP_LINK_TEXT = "SPI LLD"
