SUMMARY = "TI RTOS low level driver for Multi-channel Buffered Serial Port (McBSP)"

inherit ti-pdk ti-pdk-fetch

TI_PDK_COMP = "ti.drv.mcbsp"

PE = "1"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://mcbspver.h;beginline=1;endline=47;md5=a8a39efd13fa6fe66da5461b898d620e"

COMPATIBLE_MACHINE = "k2g|c665x-evm|omapl1"
PACKAGE_ARCH = "${MACHINE_ARCH}"

PR = "r0"


DEPENDS_append = " edma3-lld-rtos \
"

export EDMA3LLD_BIOS6_INSTALLDIR = "${EDMA3_LLD_INSTALL_DIR}"
XDCPATH_append = ";${EDMA3_LLD_INSTALL_DIR}/packages"

TI_PDK_XDCMAKE = "0"

export PDK_MCBSP_ROOT_PATH = "${WORKDIR}/build"
export DEST_ROOT = "${S}"

# HTML doc link params
PDK_COMP_LINK_TEXT = "MCBSP LLD"
