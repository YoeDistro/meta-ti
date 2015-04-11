LICENSE = "BSD-3-Clause"

LIC_FILES_CHKSUM = "file://COPYING.txt;md5=1f4217b705bf98762a302fd720649dcc"
BRANCH = "master"
SRC_URI = "git://git.ti.com/keystone-rtos/dfe-lld.git;destsuffix=git/ti/drv/dfe;protocol=git;branch=${BRANCH}"
# The following commit correspond to DEV.DFE_LLD.01.00.00.04C
SRCREV = "62da141c1cf6224d24161f0c020091043a3d7c82"

PV = "1.0.0.4"

COMPATIBLE_MACHINE = "k2l-evm"

S = "${WORKDIR}/git/ti/drv/dfe"
