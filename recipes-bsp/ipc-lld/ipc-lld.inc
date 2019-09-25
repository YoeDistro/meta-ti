inherit ti-pdk

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7eae093f2b09fd39307f30028a068b91"

COMPATIBLE_MACHINE = "k3"
PACKAGE_ARCH = "${MACHINE_ARCH}"

IPCLLD_GIT_URI = "git://git.ti.com/keystone-rtos/ipc-lld.git"
IPCLLD_GIT_PROTOCOL = "git"
IPCLLD_GIT_BRANCH = "master"

# Below commit ID corresponds to "01.00.00.00"
IPCLLD_SRCREV = "c695ca99aa4b7242e808889cfeacf848f14112c7"

BRANCH = "${IPCLLD_GIT_BRANCH}"
SRC_URI = "${IPCLLD_GIT_URI};protocol=${IPCLLD_GIT_PROTOCOL};branch=${BRANCH}"

SRCREV = "${IPCLLD_SRCREV}"
PV = "01.00.00.00"
PR = "r0"

# Build with make instead of XDC
TI_PDK_XDCMAKE = "0"

IPCLLD_PACKAGE_BASE   = "${S}/../ipclld_base"
IPCLLD_ROOTPATH = "${IPCLLD_PACKAGE_BASE}/package/all/pdk_/packages/ti/drv/ipc"

export PDK_INSTALL_PATH = "${PDK_INSTALL_DIR}/packages"
export PDK_IPC_ROOT_PATH = "${IPCLLD_PACKAGE_BASE}/package/all/pdk_/packages"

# HTML doc link params
PDK_COMP_LINK_TEXT = "IPCLLD"

EXTRA_OEMAKE = "PDK_INSTALL_PATH=${PDK_INSTALL_PATH} "
