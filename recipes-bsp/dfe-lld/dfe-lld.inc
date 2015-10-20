LICENSE = "BSD-3-Clause"

LIC_FILES_CHKSUM = "file://COPYING.txt;md5=1f4217b705bf98762a302fd720649dcc"
BRANCH = "master"
SRC_URI = "git://git.ti.com/keystone-rtos/dfe-lld.git;destsuffix=git/ti/drv/dfe;protocol=git;branch=${BRANCH}"
# The following commit correspond to DEV.DFE_LLD.01.00.00.05E
SRCREV = "42e9df0108d208c92b9d2fee7e8b1b1ab62f4b69"

PV = "1.0.0.5"

COMPATIBLE_MACHINE = "k2l-evm"

S = "${WORKDIR}/git/ti/drv/dfe"
