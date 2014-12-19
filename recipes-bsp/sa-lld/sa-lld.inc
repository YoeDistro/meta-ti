LICENSE = "BSD-3-Clause"
COMPATIBLE_MACHINE = "keystone"

LIC_FILES_CHKSUM = "file://${WORKDIR}/git/ti/drv/sa/COPYING.txt;md5=4709d353574a9569e129ebdf20e732dd"

BRANCH = "master"
SRC_URI = "git://git.ti.com/keystone-rtos/sa-lld.git;destsuffix=git/ti/drv/sa;protocol=git;branch=${BRANCH}"
# Following commit DEV.SA_LLD.03.00.00.08
SRCREV = "4da032c600ded79ab226a2a0100c1d8178a9da39"
PV = "03.00.00.08"
PR = "r0"

BASEDIR = "${WORKDIR}/git"
S = "${BASEDIR}/ti/drv/sa"
