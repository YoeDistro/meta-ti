SUMMARY = "TI Multimedia card(MMC)/Secure Digital(SD) low level driver for RTOS "

inherit ti-pdk

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://MMCSD.h;beginline=1;endline=32;md5=f74069541d4d165a000a66b4043cb065"

COMPATIBLE_MACHINE = "ti33x|ti43x|omap-a15|k2g|omapl1"
PACKAGE_ARCH = "${MACHINE_ARCH}"

MMCSD_LLD_GIT_URI = "git://git.ti.com/keystone-rtos/sd-mmc.git"
MMCSD_LLD_GIT_PROTOCOL = "git"
MMCSD_LLD_GIT_BRANCH = "master"
MMCSD_LLD_GIT_DESTSUFFIX = "git/ti/drv/mmcsd"

# Below commit ID corresponds to "DEV.MMCSD_LLD.01.00.00.07"
MMCSD_LLD_SRCREV = "54b4b40174297475a648abc4afd23560c2f29911"

BRANCH = "${MMCSD_LLD_GIT_BRANCH}"
SRC_URI = "${MMCSD_LLD_GIT_URI};destsuffix=${MMCSD_LLD_GIT_DESTSUFFIX};protocol=${MMCSD_LLD_GIT_PROTOCOL};branch=${BRANCH}"

SRCREV = "${MMCSD_LLD_SRCREV}"
PV = "01.00.00.07"
PR = "r0"

S = "${WORKDIR}/${MMCSD_LLD_GIT_DESTSUFFIX}"

DEPENDS_append = " edma3-lld-rtos \
                   osal-rtos \
"
DEPENDS_append_ti33x = " starterware-rtos \
"
DEPENDS_append_ti43x = " starterware-rtos \
"

# Build with make instead of XDC
TI_PDK_XDCMAKE = "0"

export PDK_MMCSD_ROOT_PATH ="${WORKDIR}/build"
export DEST_ROOT="${S}"

export EDMA3LLD_BIOS6_INSTALLDIR = "${EDMA3_LLD_INSTALL_DIR}"
XDCPATH_append = ";${EDMA3_LLD_INSTALL_DIR}/packages"

# HTML doc link params
PDK_COMP_LINK_TEXT = "MMCSD LLD"
