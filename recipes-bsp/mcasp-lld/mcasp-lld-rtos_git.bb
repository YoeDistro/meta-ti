SUMMARY = "TI RTOS low level driver for Multi channel Audio Serial port (McASP)"

inherit ti-pdk

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://mcaspver.h;beginline=1;endline=47;md5=25868d96216e5d6f286d5fb05a3be9f4"

COMPATIBLE_MACHINE = "ti43x|omap-a15|k2g|ti33x|omapl1|k3"
PACKAGE_ARCH = "${MACHINE_ARCH}"

MCASP_LLD_GIT_URI = "git://git.ti.com/keystone-rtos/mcasp-lld.git"
MCASP_LLD_GIT_PROTOCOL = "git"
MCASP_LLD_GIT_BRANCH = "master"
MCASP_LLD_GIT_DESTSUFFIX = "git/ti/drv/mcasp"

# Below commit ID corresponds to "DEV.MCASP_LLD.01.01.00.11A"
MCASP_LLD_SRCREV = "3620edb04240b63b50b7b9dcb3da361af8152048"

BRANCH = "${MCASP_LLD_GIT_BRANCH}"
SRC_URI = "${MCASP_LLD_GIT_URI};destsuffix=${MCASP_LLD_GIT_DESTSUFFIX};protocol=${MCASP_LLD_GIT_PROTOCOL};branch=${BRANCH}"

SRCREV = "${MCASP_LLD_SRCREV}"
PV = "01.01.00.11A"
PR = "r0"

S = "${WORKDIR}/${MCASP_LLD_GIT_DESTSUFFIX}"

DEPENDS_append = " edma3-lld-rtos \
                   osal-rtos \
"
DEPENDS_append_ti43x = " starterware-rtos \
"
DEPENDS_append_ti33x = " starterware-rtos \
"

DEPENDS_remove_k3 = "edma3-lld-rtos "
DEPENDS_append_k3 = " udma-lld-rtos \
"

# Build with make instead of XDC
TI_PDK_XDCMAKE = "0"

export PDK_MCASP_ROOT_PATH ="${WORKDIR}/build"
export DEST_ROOT="${S}"

export EDMA3LLD_BIOS6_INSTALLDIR = "${EDMA3_LLD_INSTALL_DIR}"
XDCPATH_append = ";${EDMA3_LLD_INSTALL_DIR}/packages"

# HTML doc link params
PDK_COMP_LINK_TEXT = "MCASP LLD"
