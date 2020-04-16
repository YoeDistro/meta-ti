inherit ti-pdk ti-pdk-fetch

TI_PDK_COMP = "ti.drv.ipc"

PE = "1"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7eae093f2b09fd39307f30028a068b91"

COMPATIBLE_MACHINE = "k3"
PACKAGE_ARCH = "${MACHINE_ARCH}"

PR = "r4"

# Build with make instead of XDC
TI_PDK_XDCMAKE = "0"

IPCLLD_PACKAGE_BASE   = "${WORKDIR}/ipclld_base"
IPCLLD_ROOTPATH = "${IPCLLD_PACKAGE_BASE}/package/all/pdk_/packages/ti/drv/ipc"

export PDK_INSTALL_PATH = "${PDK_INSTALL_DIR}/packages"
export PDK_IPC_ROOT_PATH = "${IPCLLD_PACKAGE_BASE}/package/all/pdk_/packages"

# HTML doc link params
PDK_COMP_LINK_TEXT = "IPCLLD"

EXTRA_OEMAKE = "PDK_INSTALL_PATH=${PDK_INSTALL_PATH} "

TI_PDK_LIMIT_CORES_append_am65xx-evm = " mcu1_1 "
