LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=2b7c96eb2aed7b0f6f922214dbde9016"

BRANCH = "master"
# This corresponds to version 01.00.02.03
SRCREV = "634a0c64763c5cd7840e2d439d0ee900455584c8"

SRC_URI = "git://git.ti.com/keystone-linux/uio-module-drv.git;protocol=git;branch=${BRANCH}"

S = "${WORKDIR}/git"
