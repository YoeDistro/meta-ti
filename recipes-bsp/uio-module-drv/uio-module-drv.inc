LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=2b7c96eb2aed7b0f6f922214dbde9016"

BRANCH = "master"
# This corresponds to version 02.03.01.00
UIO_MODULE_DRV_SRCREV = "78c535afe8f8cc0b715500a63eab0bc3606c0579"

PV = "2.3.1.0+git${SRCPV}"

UIO_MODULE_DRV_GIT_URI = "git://git.ti.com/git/keystone-linux/uio-module-drv.git"
UIO_MODULE_DRV_GIT_PROTOCOL = "https"
SRC_URI = "${UIO_MODULE_DRV_GIT_URI};protocol=${UIO_MODULE_DRV_GIT_PROTOCOL};branch=${BRANCH}"

SRCREV = "${UIO_MODULE_DRV_SRCREV}"

S = "${WORKDIR}/git"

COMPATIBLE_HOST ?= "null"
COMPATIBLE_HOST_ti-soc = "(.*)"
