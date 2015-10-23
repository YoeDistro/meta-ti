LICENSE = "BSD-3-Clause"

LIC_FILES_CHKSUM = "file://COPYING.txt;md5=5bdceac872dffdec915b819654ee23ea"
BRANCH = "master"
SRC_URI = "git://git.ti.com/keystone-rtos/aif2-lld.git;destsuffix=git/ti/drv/aif2;protocol=git;branch=${BRANCH}"
# The following commit correspond to DEV.AIF2LLD.01.02.00.00A
SRCREV = "7f394ec9975cd4cef982b11763c882c4c763ddce"

PV = "1.2.0.0"

COMPATIBLE_MACHINE = "k2hk-evm"

S = "${WORKDIR}/git/ti/drv/aif2"
