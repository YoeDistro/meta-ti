SUMMARY = "TI RTOS low lever driver for Peripheral Interconnect Express (PCIE)"

inherit ti-pdk

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://pcie.h;beginline=1;endline=34;md5=566a784d78790a716d641764d5d60b74"

COMPATIBLE_MACHINE = "omap-a15|keystone|c66x|k3"
PACKAGE_ARCH = "${MACHINE_ARCH}"

PCIE_LLD_GIT_URI = "git://git.ti.com/keystone-rtos/pcie-lld.git"
PCIE_LLD_GIT_PROTOCOL = "git"
PCIE_LLD_GIT_BRANCH = "master"
PCIE_LLD_GIT_DESTSUFFIX = "git/ti/drv/pcie"

# Below commit ID corresponds to "DEV.PCIE_LLD.02.03.00.01"
PCIE_LLD_SRCREV = "61759fc238600e3d5506ce86db1f6b604334edf8"

BRANCH = "${PCIE_LLD_GIT_BRANCH}"
SRC_URI = "${PCIE_LLD_GIT_URI};destsuffix=${PCIE_LLD_GIT_DESTSUFFIX};protocol=${PCIE_LLD_GIT_PROTOCOL};branch=${BRANCH}"

SRCREV = "${PCIE_LLD_SRCREV}"
PV = "02.03.00.01"
PR = "r0"

DEPENDS_append_k3 = " udma-lld-rtos \
"

S = "${WORKDIR}/${PCIE_LLD_GIT_DESTSUFFIX}"

export PDK_PCIE_ROOT_PATH ="${WORKDIR}/build"
export DEST_ROOT="${S}"

# Build with make instead of XDC
TI_PDK_XDCMAKE = "0"

# HTML doc link params
PDK_COMP_LINK_TEXT = "PCIe LLD"
