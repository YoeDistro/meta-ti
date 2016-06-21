DESCRIPTION = "Provides test pruss firmware for uio based tests"
LICENSE = "BSD-3-Clause"
PV = "1.0.0.0"
require recipes-ti/includes/ti-paths.inc

S = "${WORKDIR}/git"

DEPENDS = "ti-cgt-pru-native"

BRANCH = "master"
SRC_URI = "git://git.ti.com/processor-sdk/uio-test-pruss.git;protocol=git;branch=${BRANCH}"
LIC_FILES_CHKSUM = "file://Makefile;startline=1;endline=31;md5=0ee4f2c42eb6b04e37859bb55a18b144"

# Corresponds to version 01.00.00.00
SRCREV = "f04b91b84331c9a12c897e4eb2836a0a3e3a5fae"

COMPATIBLE_MACHINE = "am57xx"
EXTRA_OEMAKE = "CGT_PRU=${TI_CGT_PRU_INSTALL_DIR}"

do_install() {
    install -d ${D}${bindir}/
    cp -r *.bin ${D}${bindir}/
}
