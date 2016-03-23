DESCRIPTION = "Provides library for handling packet descriptors for keystone devices"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/COPYING.txt;md5=b7982a377c680ad71ca2fbb735982462"

DEPENDS = "common-csl-ip rm-lld qmss-lld cppi-lld"
COMPATIBLE_MACHINE = "keystone"

PKTLIB_GIT_URI = "git://git.ti.com/keystone-rtos/pktlib.git"
PKTLIB_GIT_PROTOCOL = "git"
PKTLIB_GIT_BRANCH = "master"

# Corresponds to DEV.PKTLIB.2.1.0.7
PKTLIB_SRCREV = "22f66931964c25f83d1971f7eb78902d10c17d8a"

BRANCH = "${PKTLIB_GIT_BRANCH}"
SRC_URI = "${PKTLIB_GIT_URI};protocol=${PKTLIB_GIT_PROTOCOL};branch=${BRANCH}"
SRCREV = "${PKTLIB_SRCREV}"

PV = "2.1.0.7"
PR = "r0"

S = "${WORKDIR}/git/ti/runtime/pktlib"

EXTRA_OEMAKE += "-f makefile_armv7 PDK_INSTALL_PATH=${STAGING_INCDIR} INSTALL_INC_BASE_DIR=${D}${includedir} INSTALL_LIB_BASE_DIR=${D}${libdir}"

do_compile () {
	oe_runmake lib
}

do_install () {
	oe_runmake install
}
