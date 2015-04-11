LICENSE = "BSD-3-Clause"
COMPATIBLE_MACHINE = "keystone"

LIC_FILES_CHKSUM = "file://${WORKDIR}/git/ti/drv/sa/COPYING.txt;md5=4709d353574a9569e129ebdf20e732dd"

BRANCH = "master"
SRC_URI = "git://git.ti.com/keystone-rtos/sa-lld.git;destsuffix=git/ti/drv/sa;protocol=git;branch=${BRANCH}"
# Following commit DEV.SA_LLD.03.00.00.10
SRCREV = "533aec7ef0e28c3faff4ee64a861c8935262dc33"
PV = "03.00.00.10"
PR = "r1"

BASEDIR = "${WORKDIR}/git"
S = "${BASEDIR}/ti/drv/sa"
