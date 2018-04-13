DESCRIPTION = "TI EDMA3 low level driver and test code"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING.txt;md5=5bdceac872dffdec915b819654ee23ea"

EDMA3_LLD_GIT_URI = "git://git.ti.com/keystone-rtos/edma3_lld.git"
EDMA3_LLD_GIT_PROTOCOL = "git"
EDMA3_LLD_GIT_BRANCH = "master"

# Below commit ID corresponds to version 2.12.05.30C
EDMA3_LLD_SRCREV = "a52b8f2a4bd4ca584bbd5bebba30563add353c5a"

BRANCH = "${EDMA3_LLD_GIT_BRANCH}"
SRC_URI = "${EDMA3_LLD_GIT_URI};protocol=${EDMA3_LLD_GIT_PROTOCOL};branch=${BRANCH}"
SRCREV = "${EDMA3_LLD_SRCREV}"

PV = "2.12.05.30C"
INC_PR = "r0"
