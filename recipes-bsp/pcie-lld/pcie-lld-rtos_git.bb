SUMMARY = "TI RTOS low lever driver for Peripheral Interconnect Express (PCIE)"

inherit ti-pdk ti-pdk-fetch

TI_PDK_COMP = "ti.drv.pcie"

PE = "1"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://pcie.h;beginline=1;endline=34;md5=566a784d78790a716d641764d5d60b74"

COMPATIBLE_MACHINE = "omap-a15|keystone|c66x|k3"
PACKAGE_ARCH = "${MACHINE_ARCH}"

PR = "r0"

DEPENDS_append_k3 = " udma-lld-rtos \
"


export PDK_PCIE_ROOT_PATH ="${WORKDIR}/build"
export DEST_ROOT="${S}"

# Build with make instead of XDC
TI_PDK_XDCMAKE = "0"

# HTML doc link params
PDK_COMP_LINK_TEXT = "PCIe LLD"
