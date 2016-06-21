LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=2b7c96eb2aed7b0f6f922214dbde9016"

BRANCH = "master"
# This corresponds to version 02.01.00.00
SRCREV = "19761495883bf8fb2670b0929d255135bd295dcb"

PV = "2.1.0.0+git${SRCPV}"

SRC_URI = "git://git.ti.com/keystone-linux/uio-module-drv.git;protocol=git;branch=${BRANCH}"

S = "${WORKDIR}/git"
