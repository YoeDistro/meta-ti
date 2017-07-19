SUMMARY = "TI RTOS low level driver for Multi channel Audio Serial port (McASP)"

inherit ti-pdk

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://mcaspver.h;beginline=1;endline=47;md5=cc81a11c1566fe1cf19270744f504619"

COMPATIBLE_MACHINE = "ti43x|omap-a15|k2g|ti33x|omapl1"
PACKAGE_ARCH = "${MACHINE_ARCH}"

MCASP_LLD_GIT_URI = "git://git.ti.com/keystone-rtos/mcasp-lld.git"
MCASP_LLD_GIT_PROTOCOL = "git"
MCASP_LLD_GIT_BRANCH = "master"
MCASP_LLD_GIT_DESTSUFFIX = "git/ti/drv/mcasp"

# Below commit ID corresponds to "DEV.MCASP_LLD.01.01.00.06B"
MCASP_LLD_SRCREV = "c64ca018d4482f6841e7f8fde3a7adf50f4d329f"

BRANCH = "${MCASP_LLD_GIT_BRANCH}"
SRC_URI = "${MCASP_LLD_GIT_URI};destsuffix=${MCASP_LLD_GIT_DESTSUFFIX};protocol=${MCASP_LLD_GIT_PROTOCOL};branch=${BRANCH}"

SRCREV = "${MCASP_LLD_SRCREV}"
PV = "01.01.00.06B"
PR = "r0"

S = "${WORKDIR}/${MCASP_LLD_GIT_DESTSUFFIX}"

DEPENDS_append = " edma3-lld-rtos \
                   osal-rtos \
"
DEPENDS_append_ti43x = " starterware-rtos \
"
DEPENDS_append_ti33x = " starterware-rtos \
"
# Build with make instead of XDC
TI_PDK_XDCMAKE = "0"

export PDK_MCASP_ROOT_PATH ="${WORKDIR}/build"
export DEST_ROOT="${S}"

export EDMA3LLD_BIOS6_INSTALLDIR = "${EDMA3_LLD_INSTALL_DIR}"
XDCPATH_append = ";${EDMA3_LLD_INSTALL_DIR}/packages"

# HTML doc link params
PDK_COMP_LINK_TEXT = "MCASP LLD"
