DESCRIPTION = "TI Resource Manager Low Level Driver"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/ti/drv/rm/COPYING.txt;md5=dc61631b65360e6beb73b6c337800afc"

RM_LLD_GIT_URI = "git://git.ti.com/keystone-rtos/rm-lld.git"
RM_LLD_GIT_PROTOCOL = "git"
RM_LLD_GIT_BRANCH = "master"
RM_LLD_GIT_DESTSUFFIX = "git/ti/drv/rm"

# Below commit ID corresponds to "DEV.RM_LLD.02.02.00.03A"
RM_LLD_SRCREV = "c90a9a8c7398e863571dc6af46d5b23389239ec5"

BRANCH = "${RM_LLD_GIT_BRANCH}"
SRC_URI = "${RM_LLD_GIT_URI};destsuffix=${RM_LLD_GIT_DESTSUFFIX};protocol=${RM_LLD_GIT_PROTOCOL};branch=${BRANCH}"
SRCREV = "${RM_LLD_SRCREV}"

PV = "02.02.00.03A"
INC_PR = "r0"

COMPATIBLE_MACHINE = "keystone"

BASEDIR = "${WORKDIR}/git"
S = "${BASEDIR}/ti/drv/rm"
