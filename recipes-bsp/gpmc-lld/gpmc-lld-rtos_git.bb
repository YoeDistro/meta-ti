SUMMARY = "TI RTOS low level driver for General Purpose Memory Controller (GPMC)"

inherit ti-pdk

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://GPMC.h;beginline=1;endline=31;md5=485ec97c36f809bf92323fde3e988c72"

COMPATIBLE_MACHINE = "ti33x|ti43x"
PACKAGE_ARCH = "${MACHINE_ARCH}"

GPMC_LLD_GIT_URI = "git://git.ti.com/keystone-rtos/gpmc-lld.git"
GPMC_LLD_GIT_PROTOCOL = "git"
GPMC_LLD_GIT_BRANCH = "master"

# Below commit ID corresponds to "DEV.GPMC_LLD.01.00.00.02"
GPMC_LLD_SRCREV = "320b18c703f70e01ea1b487c7bf9c4e08e7ae2df"

BRANCH = "${GPMC_LLD_GIT_BRANCH}"
SRC_URI = "${GPMC_LLD_GIT_URI};protocol=${GPMC_LLD_GIT_PROTOCOL};branch=${BRANCH}"

SRCREV = "${GPMC_LLD_SRCREV}"
PV = "01.00.00.02"
PR = "r0"

DEPENDS_append = " osal-rtos \
"
DEPENDS_append_ti33x = " starterware-rtos \
"
DEPENDS_append_ti43x = " starterware-rtos \
"

# Build with make instead of XDC
TI_PDK_XDCMAKE = "0"

# HTML doc link params
PDK_COMP_LINK_TEXT = "GPMC LLD"

export PDK_GPMC_ROOT_PATH ="${WORKDIR}/build"
export DEST_ROOT="${S}"
