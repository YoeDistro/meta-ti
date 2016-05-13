LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=2b7c96eb2aed7b0f6f922214dbde9016"

BRANCH = "master"
# This corresponds to version 02.00.00.00
SRCREV = "6a7eda36e390658593a5b8d87b0f00beb3997240"

PV = "2.0.0.0+git${SRCPV}"

SRC_URI = "git://git.ti.com/keystone-linux/uio-module-drv.git;protocol=git;branch=${BRANCH}"

S = "${WORKDIR}/git"
