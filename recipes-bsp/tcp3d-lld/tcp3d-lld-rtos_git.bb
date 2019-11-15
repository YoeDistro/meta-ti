SUMMARY = "TI third generation Turbo Coprocessor (TCP3) low level driver"

inherit ti-pdk ti-pdk-fetch

TI_PDK_COMP = "ti.drv.tcp3d"

PE = "1"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://tcp3dver.h;beginline=1;endline=47;md5=3fe46c6320fb9ca4ac6692961402beb7"

COMPATIBLE_MACHINE = "k2hk|k2l|c66x"
PACKAGE_ARCH = "${MACHINE_ARCH}"

PR = "r0"

DEPENDS_append = " edma3-lld-rtos"

export EDMA3LLD_BIOS6_INSTALLDIR = "${EDMA3_LLD_INSTALL_DIR}"
XDCPATH_append = ";${EDMA3_LLD_INSTALL_DIR}/packages"

# HTML doc link params
PDK_COMP_LINK_TEXT = "TCP3D LLD"
