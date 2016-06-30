LICENSE = "BSD-3-Clause"

LIC_FILES_CHKSUM = "file://COPYING.txt;md5=1f4217b705bf98762a302fd720649dcc"

DFE_LLD_GIT_URI = "git://git.ti.com/keystone-rtos/dfe-lld.git"
DFE_LLD_GIT_PROTOCOL = "git"
DFE_LLD_GIT_BRANCH = "master"
DFE_LLD_GIT_DESTSUFFIX = "git/ti/drv/dfe"

# Below commit ID corresponds to "DEV.DFE_LLD.01.00.00.08"
DFE_LLD_SRCREV = "d51482ef18889bbbd8e9cdc207c6fe9e27b1ab03"

BRANCH = "${DFE_LLD_GIT_BRANCH}"
SRC_URI = "${DFE_LLD_GIT_URI};destsuffix=${DFE_LLD_GIT_DESTSUFFIX};protocol=${DFE_LLD_GIT_PROTOCOL};branch=${BRANCH}"
SRCREV = "${DFE_LLD_SRCREV}"

PV = "1.0.0.8"
INC_PR = "r0"

COMPATIBLE_MACHINE = "k2l-evm"

S = "${WORKDIR}/${DFE_LLD_GIT_DESTSUFFIX}"
