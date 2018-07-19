SUMMARY = "TI third generation Turbo Coprocessor (TCP3) low level driver"

inherit ti-pdk

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://tcp3dver.h;beginline=1;endline=47;md5=3fe46c6320fb9ca4ac6692961402beb7"

COMPATIBLE_MACHINE = "k2hk|k2l|c66x"
PACKAGE_ARCH = "${MACHINE_ARCH}"

TCP3D_LLD_GIT_URI = "git://git.ti.com/keystone-rtos/tcp3d-lld.git"
TCP3D_LLD_GIT_PROTOCOL = "git"
TCP3D_LLD_GIT_BRANCH = "master"
TCP3D_LLD_GIT_DESTSUFFIX = "git/ti/drv/bcp"

# Below commit ID corresponds to "DEV.TCP3D_LLD.02.01.00.06"
TCP3D_LLD_SRCREV = "f76b6da0a31e4232c0f728703f8dece3d8d88aaa"

BRANCH = "${TCP3D_LLD_GIT_BRANCH}"
SRC_URI = "${TCP3D_LLD_GIT_URI};destsuffix=${TCP3D_LLD_GIT_DESTSUFFIX};protocol=${TCP3D_LLD_GIT_PROTOCOL};branch=${BRANCH}"

SRCREV = "${TCP3D_LLD_SRCREV}"
PV = "02.01.00.06"
PR = "r0"

S = "${WORKDIR}/${TCP3D_LLD_GIT_DESTSUFFIX}"

DEPENDS_append = " edma3-lld-rtos"

export EDMA3LLD_BIOS6_INSTALLDIR = "${EDMA3_LLD_INSTALL_DIR}"
XDCPATH_append = ";${EDMA3_LLD_INSTALL_DIR}/packages"

# HTML doc link params
PDK_COMP_LINK_TEXT = "TCP3D LLD"
