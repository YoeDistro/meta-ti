LICENSE = "BSD-3-Clause"

LIC_FILES_CHKSUM = "file://COPYING.txt;md5=1f4217b705bf98762a302fd720649dcc"
BRANCH = "master"
SRC_URI = "git://git.ti.com/keystone-rtos/dfe-lld.git;destsuffix=git/ti/drv/dfe;protocol=git;branch=${BRANCH}"
# The following commit correspond to DEV.DFE_LLD.01.00.00.03C
SRCREV = "ed20e87d3676e3e05ce2b78179b504a05083ad15"

PV = "1.0.0.3"

COMPATIBLE_MACHINE = "k2l-evm"

S = "${WORKDIR}/git/ti/drv/dfe"
