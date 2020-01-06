SUMMARY = "TI RTOS low lever driver for Peripheral Interconnect Express (PCIE)"

inherit ti-pdk ti-pdk-fetch

TI_PDK_COMP = "ti.drv.pcie"

PE = "1"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://pcie.h;beginline=1;endline=34;md5=232c0b07bdf7ab934b23c09e6c5279b0"

COMPATIBLE_MACHINE = "omap-a15|keystone|c66x|k3"
PACKAGE_ARCH = "${MACHINE_ARCH}"

PR = "r1"

DEPENDS_append_k3 = " udma-lld-rtos \
"


export PDK_PCIE_ROOT_PATH ="${WORKDIR}/build"
export DEST_ROOT="${S}"

# Build with make instead of XDC
TI_PDK_XDCMAKE = "0"

# HTML doc link params
PDK_COMP_LINK_TEXT = "PCIe LLD"
