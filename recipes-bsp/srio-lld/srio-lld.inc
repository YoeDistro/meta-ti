LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/ti/drv/srio/srio_drv.h;beginline=1;endline=41;md5=469096088e072d883ff7eef301b34093"

COMPATIBLE_MACHINE = "k2hk"

SRIO_LLD_GIT_URI = "git://git.ti.com/keystone-rtos/srio-lld.git"
SRIO_LLD_GIT_PROTOCOL = "git"
SRIO_LLD_GIT_BRANCH = "master"
SRIO_LLD_GIT_DESTSUFFIX = "git/ti/drv/srio"

# commit ID corresponding to "DEV.SRIO_LLD.02.00.00.17"
SRIO_LLD_SRCREV = "444fe581426c04c9d57f9bce6763ebe43ce284f8"

BRANCH = "${SRIO_LLD_GIT_BRANCH}"
SRC_URI = "${SRIO_LLD_GIT_URI};destsuffix=${SRIO_LLD_GIT_DESTSUFFIX};protocol=${SRIO_LLD_GIT_PROTOCOL};branch=${BRANCH}"
SRCREV = "${SRIO_LLD_SRCREV}"

PV = "02.00.00.17"
INC_PR = "r0"

DEVICELIST = "k2h k2k"

BASEDIR = "${WORKDIR}/git"
S = "${BASEDIR}/ti/drv/srio"

EXTRA_OEMAKE = "-f makefile_armv7 PDK_INSTALL_PATH=${STAGING_INCDIR}"
