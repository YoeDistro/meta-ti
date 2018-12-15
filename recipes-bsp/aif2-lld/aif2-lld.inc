LICENSE = "BSD-3-Clause"

LIC_FILES_CHKSUM = "file://COPYING.txt;md5=5bdceac872dffdec915b819654ee23ea"

AIF2_LLD_GIT_URI = "git://git.ti.com/keystone-rtos/aif2-lld.git"
AIF2_LLD_GIT_PROTOCOL = "git"
AIF2_LLD_GIT_BRANCH = "master"
AIF2_LLD_GIT_DESTSUFFIX = "git/ti/drv/aif2"

# The following commit correspond to DEV.AIF2LLD.01.02.00.02A
AIF2_LLD_SRCREV = "3eecba9b3b07b0c8fc39daa79fe4e8204e5d0e0e"

BRANCH = "${AIF2_LLD_GIT_BRANCH}"
SRC_URI = "${AIF2_LLD_GIT_URI};destsuffix=${AIF2_LLD_GIT_DESTSUFFIX};protocol=${AIF2_LLD_GIT_PROTOCOL};branch=${BRANCH}"
SRCREV = "${AIF2_LLD_SRCREV}"

PV = "1.2.0.2A"
INC_PR = "r0"

COMPATIBLE_MACHINE = "k2hk"

S = "${WORKDIR}/${AIF2_LLD_GIT_DESTSUFFIX}"
