LIC_FILES_CHKSUM = "file://COPYING.txt;md5=5bdceac872dffdec915b819654ee23ea"
LICENSE = "BSD-3-Clause"

IQN2_LLD_GIT_URI = "git://git.ti.com/keystone-rtos/iqn2-lld.git"
IQN2_LLD_GIT_PROTOCOL = "git"
IQN2_LLD_GIT_BRANCH = "master"
IQN2_LLD_GIT_DESTSUFFIX = "git/ti/drv/iqn2"

# Following commit corresponds to DEV.IQN_LLD.01.00.00.11A
IQN2_LLD_SRCREV = "41c0cd3cb4fc25453904f8c583ebd0a7a0e5b940"

BRANCH = "${IQN2_LLD_GIT_BRANCH}"
SRC_URI = "${IQN2_LLD_GIT_URI};destsuffix=${IQN2_LLD_GIT_DESTSUFFIX};protocol=${IQN2_LLD_GIT_PROTOCOL};branch=${BRANCH}"
SRCREV = "${IQN2_LLD_SRCREV}"

PV = "1.0.0.11A"
INC_PR = "r0"

COMPATIBLE_MACHINE = "k2l"

S = "${WORKDIR}/${IQN2_LLD_GIT_DESTSUFFIX}"
