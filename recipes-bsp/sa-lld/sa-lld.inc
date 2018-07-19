LICENSE = "BSD-3-Clause & TI-TFL"
COMPATIBLE_MACHINE = "keystone"

LIC_FILES_CHKSUM = "file://${WORKDIR}/git/ti/drv/sa/COPYING.txt;md5=cd04fb087063a2a82c4ce61983836871"

SA_LLD_GIT_URI = "git://git.ti.com/keystone-rtos/sa-lld.git"
SA_LLD_GIT_PROTOCOL = "git"
SA_LLD_GIT_BRANCH = "master"
SA_LLD_GIT_DESTSUFFIX = "git/ti/drv/sa"

# Below commit ID corresponding to "DEV.SA_LLD.03.00.00.21"
SA_LLD_SRCREV = "7ad90025beacbafefd4a41bbe22525d8b55f7635"

BRANCH = "${SA_LLD_GIT_BRANCH}"
SRC_URI = "${SA_LLD_GIT_URI};destsuffix=${SA_LLD_GIT_DESTSUFFIX};protocol=${SA_LLD_GIT_PROTOCOL};branch=${BRANCH}"
SRCREV = "${SA_LLD_SRCREV}"

PV = "03.00.00.21"
INC_PR = "r0"

S = "${WORKDIR}/${SA_LLD_GIT_DESTSUFFIX}"
