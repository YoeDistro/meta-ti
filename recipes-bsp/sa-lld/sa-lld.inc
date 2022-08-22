LICENSE = "BSD-3-Clause & TI-TFL"
COMPATIBLE_MACHINE = "k2hk|k2e|k2l"

LIC_FILES_CHKSUM = "file://${WORKDIR}/git/ti/drv/sa/COPYING.txt;md5=cd04fb087063a2a82c4ce61983836871"

SA_LLD_GIT_URI = "git://git.ti.com/git/keystone-rtos/sa-lld.git"
SA_LLD_GIT_PROTOCOL = "https"
SA_LLD_GIT_BRANCH = "master"
SA_LLD_GIT_DESTSUFFIX = "git/ti/drv/sa"

# Below commit ID corresponding to "DEV.SA_LLD.04.00.00.03D"
SA_LLD_SRCREV = "506491b586c13c90f307b527df5eaacd35af6d02"

BRANCH = "${SA_LLD_GIT_BRANCH}"
SRC_URI = "${SA_LLD_GIT_URI};destsuffix=${SA_LLD_GIT_DESTSUFFIX};protocol=${SA_LLD_GIT_PROTOCOL};branch=${BRANCH}"
SRCREV = "${SA_LLD_SRCREV}"

PV = "04.00.00.03D"
INC_PR = "r0"

S = "${WORKDIR}/${SA_LLD_GIT_DESTSUFFIX}"
