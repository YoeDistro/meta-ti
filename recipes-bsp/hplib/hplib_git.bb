DESCRIPTION = "TI High performance libraries"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/ti/runtime/hplib/src/COPYING.txt;md5=b7982a377c680ad71ca2fbb735982462"

DEPENDS = "common-csl-ip rm-lld qmss-lld sa-lld pktlib"
RDEPENDS_${PN} = "hplib-mod"

include hplib.inc

PR = "${INC_PR}.0"

CHOICELIST = "yes no"

S = "${WORKDIR}/git/ti/runtime/hplib"

EXTRA_OEMAKE += "-f makefile_armv7 PDK_INSTALL_PATH=${STAGING_INCDIR}"

do_compile () {
	for device in ${DEVICELIST}
	do
		for choice in ${CHOICELIST}
		do
			oe_runmake lib HPLIB_SRC_DIR=${S} USEDYNAMIC_LIB=$choice DEVICE="$device"
		done
	done
}

do_install () {
	for device in ${DEVICELIST}
	do
		oe_runmake install INSTALL_INC_BASE_DIR=${D}${includedir} INSTALL_LIB_BASE_DIR=${D}${libdir} INSTALL_BIN_BASE_DIR=${D}${bindir} DEVICE="$device"
	done
}
