DESCRIPTION = "Provides library for handling packet descriptors for keystone devices"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/COPYING.txt;md5=b7982a377c680ad71ca2fbb735982462"

DEPENDS = "common-csl-ip rm-lld qmss-lld cppi-lld"
COMPATIBLE_MACHINE = "keystone"

BRANCH = "master"
SRC_URI = "git://git.ti.com/keystone-rtos/pktlib.git;protocol=git;branch=${BRANCH}"

# Corresponds to DEV.PKTLIB.2.1.0.4
SRCREV = "a9aa56afed54b0a04f36d2fdb248cad5dcac6ff6"
PV = "2.1.0.4"

S = "${WORKDIR}/git/ti/runtime/pktlib"

EXTRA_OEMAKE += "-f makefile_armv7 PDK_INSTALL_PATH=${STAGING_INCDIR} INSTALL_INC_BASE_DIR=${D}${includedir} INSTALL_LIB_BASE_DIR=${D}${libdir}"

do_compile () {
	oe_runmake lib
}

do_install () {
	oe_runmake install
}
