LIC_FILES_CHKSUM = "file://COPYING.txt;md5=5bdceac872dffdec915b819654ee23ea"
LICENSE = "BSD-3-Clause"

BRANCH = "master"
SRC_URI = "git://git.ti.com/keystone-rtos/iqn2-lld.git;destsuffix=git/ti/drv/iqn2;protocol=git;branch=${BRANCH}"
# Following commit corresponds to DEV.IQN_LLD.01.00.00.08F
SRCREV = "29d5358b944f1447c024263f86e8c71784440b99"

PV = "1.0.0.8"

COMPATIBLE_MACHINE = "k2l-evm"

S = "${WORKDIR}/git/ti/drv/iqn2"
