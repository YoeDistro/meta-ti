LICENSE = "BSD-3-Clause"
COMPATIBLE_MACHINE = "keystone"

LIC_FILES_CHKSUM = "file://${WORKDIR}/git/ti/drv/sa/COPYING.txt;md5=4709d353574a9569e129ebdf20e732dd"

SA_LLD_GIT_URI = "git://git.ti.com/keystone-rtos/sa-lld.git"
SA_LLD_GIT_PROTOCOL = "git"
SA_LLD_GIT_BRANCH = "master"
SA_LLD_GIT_DESTSUFFIX = "git/ti/drv/sa"

# Following commit DEV.SA_LLD.03.00.00.11A
SA_LLD_SRCREV = "bf5b097e868b20d23d386abc34a1902903d7d9ea"

BRANCH = "${SA_LLD_GIT_BRANCH}"
SRC_URI = "${SA_LLD_GIT_URI};destsuffix=${SA_LLD_GIT_DESTSUFFIX};protocol=${SA_LLD_GIT_PROTOCOL};branch=${BRANCH}"
SRCREV = "${SA_LLD_SRCREV}"

PV = "03.00.00.11"
INC_PR = "r1"

S = "${WORKDIR}/${SA_LLD_GIT_DESTSUFFIX}"
