DESCRIPTION = "TI EDMA3 low level driver and test code"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING.txt;md5=5bdceac872dffdec915b819654ee23ea"

EDMA3_LLD_GIT_URI = "git://git.ti.com/keystone-rtos/edma3_lld.git"
EDMA3_LLD_GIT_PROTOCOL = "git"
EDMA3_LLD_GIT_BRANCH = "master"

# The following commit corresponds to tag DEV_EDMA3_LLD_02_12_01_23A
EDMA3_LLD_SRCREV = "84c84a82332b30ece3b00f7d769270466ae836e0"

BRANCH = "${EDMA3_LLD_GIT_BRANCH}"
SRC_URI = "${EDMA3_LLD_GIT_URI};protocol=${EDMA3_LLD_GIT_PROTOCOL};branch=${BRANCH}"
SRCREV = "${EDMA3_LLD_SRCREV}"

PV = "2.12.01.23"
INC_PR = "r0"
