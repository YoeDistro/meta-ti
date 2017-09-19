LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=2b7c96eb2aed7b0f6f922214dbde9016"

BRANCH = "master"
# This corresponds to version 02.02.01.00
UIO_MODULE_DRV_SRCREV = "bda9260f2220840a60ec0fbc8011cbadf3cf1b91"

PV = "2.2.1.0+git${SRCPV}"

UIO_MODULE_DRV_GIT_URI = "git://git.ti.com/keystone-linux/uio-module-drv.git"
UIO_MODULE_DRV_GIT_PROTOCOL = "git"
SRC_URI = "${UIO_MODULE_DRV_GIT_URI};protocol=${UIO_MODULE_DRV_GIT_PROTOCOL};branch=${BRANCH}"

SRCREV = "${UIO_MODULE_DRV_SRCREV}"

S = "${WORKDIR}/git"
