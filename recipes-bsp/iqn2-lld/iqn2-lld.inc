LIC_FILES_CHKSUM = "file://COPYING.txt;md5=5bdceac872dffdec915b819654ee23ea"
LICENSE = "BSD-3-Clause"

BRANCH = "master"
SRC_URI = "git://git.ti.com/keystone-rtos/iqn2-lld.git;destsuffix=git/ti/drv/iqn2;protocol=git;branch=${BRANCH}"
# Following commit corresponds to DEV.IQN_LLD.01.00.00.07A
SRCREV = "94017e30c87853e48f6e947186c5075289b190c6"

PV = "1.0.0.7"

COMPATIBLE_MACHINE = "k2l-evm"

S = "${WORKDIR}/git/ti/drv/iqn2"
