DESCRIPTION = "TI EDMA3 low level driver and test code"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING.txt;md5=5bdceac872dffdec915b819654ee23ea"

EDMA3_LLD_GIT_URI = "git://git.ti.com/keystone-rtos/edma3_lld.git"
EDMA3_LLD_GIT_PROTOCOL = "git"
EDMA3_LLD_GIT_BRANCH = "master"

# The following commit corresponds to tag DEV_EDMA3_LLD_02_12_01_21_RC1
EDMA3_LLD_SRCREV = "9a94177958b9269b1b8aeeb2a5d362ca4e64dafa"

BRANCH = "${EDMA3_LLD_GIT_BRANCH}"
SRC_URI = "${EDMA3_LLD_GIT_URI};protocol=${EDMA3_LLD_GIT_PROTOCOL};branch=${BRANCH}"
SRCREV = "${EDMA3_LLD_SRCREV}"

PV = "2.12.01.22"
INC_PR = "r0"
