LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/ti/drv/pa/COPYING.txt;md5=e8f6789acdcda76d02ed9203fc2e603d"

COMPATIBLE_MACHINE = "keystone"

PA_LLD_GIT_URI = "git://git.ti.com/keystone-rtos/pa-lld.git"
PA_LLD_GIT_PROTOCOL = "git"
PA_LLD_GIT_BRANCH = "master"
PA_LLD_GIT_DESTSUFFIX = "git/ti/drv/pa"

# Below Commit ID corresponds to "DEV.PA_LLD.03.00.01.05A"
PA_LLD_SRCREV = "ae3aa3e80ecf64fe5a16515d134a6bc217220e2e"

BRANCH = "${PA_LLD_GIT_BRANCH}"
SRC_URI = "${PA_LLD_GIT_URI};destsuffix=${PA_LLD_GIT_DESTSUFFIX};protocol=${PA_LLD_GIT_PROTOCOL};branch=${BRANCH}"
SRCREV = "${PA_LLD_SRCREV}"

PV = "03.00.01.05"
INC_PR = "r2"

S = "${WORKDIR}/${PA_LLD_GIT_DESTSUFFIX}"
