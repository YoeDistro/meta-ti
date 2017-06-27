LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=2b7c96eb2aed7b0f6f922214dbde9016"

BRANCH = "master"
# This corresponds to version 02.02.00.00
UIO_MODULE_DRV_SRCREV = "59cd07395d7f26cbd01969d130d7b8bd5ccb12c5"

PV = "2.2.0.0+git${SRCPV}"

UIO_MODULE_DRV_GIT_URI = "git://git.ti.com/keystone-linux/uio-module-drv.git"
UIO_MODULE_DRV_GIT_PROTOCOL = "git"
SRC_URI = "${UIO_MODULE_DRV_GIT_URI};protocol=${UIO_MODULE_DRV_GIT_PROTOCOL};branch=${BRANCH}"

SRCREV = "${UIO_MODULE_DRV_SRCREV}"

S = "${WORKDIR}/git"
