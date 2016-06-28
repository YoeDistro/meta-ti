DESCRIPTION = "TI Resource Manager Low Level Driver"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/ti/drv/rm/COPYING.txt;md5=dc61631b65360e6beb73b6c337800afc"

RM_LLD_GIT_URI = "git://git.ti.com/keystone-rtos/rm-lld.git"
RM_LLD_GIT_PROTOCOL = "git"
RM_LLD_GIT_BRANCH = "master"
RM_LLD_GIT_DESTSUFFIX = "git/ti/drv/rm"

# Below commit ID corresponds to "DEV.RM_LLD.02.02.00.02"
RM_LLD_SRCREV = "6165053f88d1ccb5083f797d24800d5400804647"

BRANCH = "${RM_LLD_GIT_BRANCH}"
SRC_URI = "${RM_LLD_GIT_URI};destsuffix=${RM_LLD_GIT_DESTSUFFIX};protocol=${RM_LLD_GIT_PROTOCOL};branch=${BRANCH}"
SRCREV = "${RM_LLD_SRCREV}"

PV = "02.02.00.02"
INC_PR = "r1"

COMPATIBLE_MACHINE = "keystone"

BASEDIR = "${WORKDIR}/git"
S = "${BASEDIR}/ti/drv/rm"
