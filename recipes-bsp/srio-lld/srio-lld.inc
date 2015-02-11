LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/ti/drv/srio/srio_drv.h;beginline=1;endline=41;md5=469096088e072d883ff7eef301b34093"

BRANCH = "master"
SRC_URI = "git://git.ti.com/keystone-rtos/srio-lld.git;destsuffix=git/ti/drv/srio;protocol=git;branch=${BRANCH}"

# commit ID corresponding to DEV.SRIO_LLD.02.00.00.11
SRCREV = "2d0efea281222055b3b828c79e57102edaae9db7"
PV = "02.00.00.11"

DEVICELIST = "k2h k2k"

BASEDIR = "${WORKDIR}/git"
S = "${BASEDIR}/ti/drv/srio"

EXTRA_OEMAKE = "-f makefile_armv7 PDK_INSTALL_PATH=${STAGING_INCDIR}"
