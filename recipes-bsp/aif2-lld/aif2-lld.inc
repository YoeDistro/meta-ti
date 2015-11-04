LICENSE = "BSD-3-Clause"

LIC_FILES_CHKSUM = "file://COPYING.txt;md5=5bdceac872dffdec915b819654ee23ea"

AIF2_LLD_GIT_URI = "git://git.ti.com/keystone-rtos/aif2-lld.git"
AIF2_LLD_GIT_PROTOCOL = "git"
AIF2_LLD_GIT_BRANCH = "master"
AIF2_LLD_GIT_DESTSUFFIX = "git/ti/drv/aif2"

# The following commit correspond to DEV.AIF2LLD.01.02.00.00A
AIF2_LLD_SRCREV = "7f394ec9975cd4cef982b11763c882c4c763ddce"

BRANCH = "${AIF2_LLD_GIT_BRANCH}"
SRC_URI = "${AIF2_LLD_GIT_URI};destsuffix=${AIF2_LLD_GIT_DESTSUFFIX};protocol=${AIF2_LLD_GIT_PROTOCOL};branch=${BRANCH}"
SRCREV = "${AIF2_LLD_SRCREV}"

PV = "1.2.0.0"
INC_PR = "r1"

COMPATIBLE_MACHINE = "k2hk-evm"

S = "${WORKDIR}/${AIF2_LLD_GIT_DESTSUFFIX}"
