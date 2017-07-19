SUMMARY = "TI RTOS low level driver for Multi-channel Buffered Serial Port (McBSP)"

inherit ti-pdk

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://mcbspver.h;beginline=1;endline=47;md5=a8a39efd13fa6fe66da5461b898d620e"

COMPATIBLE_MACHINE = "k2g|c665x-evm"
PACKAGE_ARCH = "${MACHINE_ARCH}"

MCBSP_LLD_GIT_URI = "git://git.ti.com/keystone-rtos/mcbsp-lld.git"
MCBSP_LLD_GIT_PROTOCOL = "git"
MCBSP_LLD_GIT_BRANCH = "master"
MCBSP_LLD_GIT_DESTSUFFIX = "git/ti/drv/mcbsp"

# Below commit ID corresponds to "DEV.MCBSP_LLD.01.00.00.08"
MCBSP_LLD_SRCREV = "63a4bed526ac70bbc530836ef4aa711739bd0bd2"

BRANCH = "${MCBSP_LLD_GIT_BRANCH}"
SRC_URI = "${MCBSP_LLD_GIT_URI};destsuffix=${MCBSP_LLD_GIT_DESTSUFFIX};protocol=${MCBSP_LLD_GIT_PROTOCOL};branch=${BRANCH}"

SRCREV = "${MCBSP_LLD_SRCREV}"
PV = "01.00.00.08"
PR = "r0"

S = "${WORKDIR}/${MCBSP_LLD_GIT_DESTSUFFIX}"

DEPENDS_append = " edma3-lld-rtos \
"

export EDMA3LLD_BIOS6_INSTALLDIR = "${EDMA3_LLD_INSTALL_DIR}"
XDCPATH_append = ";${EDMA3_LLD_INSTALL_DIR}/packages"

# HTML doc link params
PDK_COMP_LINK_TEXT = "MCBSP LLD"
