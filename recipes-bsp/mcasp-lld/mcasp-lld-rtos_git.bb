SUMMARY = "TI RTOS low level driver for Multi channel Audio Serial port (McASP)"

inherit ti-pdk ti-pdk-fetch

TI_PDK_COMP = "ti.drv.mcasp"

PE = "1"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://mcaspver.h;beginline=19;endline=47;md5=75a9adc782a6df0d3a5419743e9a9f18"

COMPATIBLE_MACHINE = "ti43x|omap-a15|k2g|ti33x|omapl1|k3"
PACKAGE_ARCH = "${MACHINE_ARCH}"


PR = "r0"


DEPENDS_append = " edma3-lld-rtos \
                   osal-rtos \
"
DEPENDS_append_ti43x = " starterware-rtos \
"
DEPENDS_append_ti33x = " starterware-rtos \
"

DEPENDS_remove_k3 = "edma3-lld-rtos "
DEPENDS_append_k3 = " udma-lld-rtos \
"

# Build with make instead of XDC
TI_PDK_XDCMAKE = "0"

export PDK_MCASP_ROOT_PATH ="${WORKDIR}/build"
export DEST_ROOT="${S}"

export EDMA3LLD_BIOS6_INSTALLDIR = "${EDMA3_LLD_INSTALL_DIR}"
XDCPATH_append = ";${EDMA3_LLD_INSTALL_DIR}/packages"

# HTML doc link params
PDK_COMP_LINK_TEXT = "MCASP LLD"
