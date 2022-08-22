DESCRIPTION = "TI EDMA3 low level driver and test code"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING.txt;md5=5bdceac872dffdec915b819654ee23ea"

EDMA3_LLD_GIT_URI = "git://git.ti.com/git/keystone-rtos/edma3_lld.git"
EDMA3_LLD_GIT_PROTOCOL = "https"
EDMA3_LLD_GIT_BRANCH = "master"

# Below commit ID corresponds to version 2.12.05.30E
EDMA3_LLD_SRCREV = "ce5ced2ac5eb1e0f1515288d279f5690813b5413"

BRANCH = "${EDMA3_LLD_GIT_BRANCH}"
SRC_URI = "${EDMA3_LLD_GIT_URI};protocol=${EDMA3_LLD_GIT_PROTOCOL};branch=${BRANCH}"
SRCREV = "${EDMA3_LLD_SRCREV}"

PV = "2.12.05.30E"
INC_PR = "r1"
