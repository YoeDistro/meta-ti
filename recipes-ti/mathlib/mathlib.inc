SUMMARY = "MATHLIB contains optimized floating-point math functions for TI DSPs"
HOMEPAGE = "http://git.ti.com/ep-processor-libraries/mathlib"
LICENSE = "BSD-3-Clause"

require ../includes/ti-paths.inc

PV = "3_1_2_2"
PR = "r0"

DEPENDS = "ti-cgt6x-native \
           ti-xdctools-native \
           ti-sysbios \
           doxygen-native \
           zip-native \
           swtools"

S = "${WORKDIR}/git"

SRC_URI = "git://git.ti.com/ep-processor-libraries/mathlib.git;protocol=git;branch=master"
SRCREV = "195355db20f53295cfeef0bcfba0a8d8fdb0a753"

LIC_FILES_CHKSUM = "file://ti/mathlib/src/acosdp/acosdp.h;beginline=1;endline=35;md5=c133f7e139b46385906c861dd9552250"

export MATHLIB_WORK_DIR="${S}"
export CGTOOLS_INSTALL_DIR  = "${STAGING_DIR_NATIVE}/usr/share/ti/cgt-c6x"
export C64PCODEGENTOOL="${CGTOOLS_INSTALL_DIR}"
export C674CODEGENTOOL="${CGTOOLS_INSTALL_DIR}"
export C66CODEGENTOOL ="${CGTOOLS_INSTALL_DIR}"
export SWTOOLS_PATH = "${SWTOOLS_INSTALL_DIR}/ti/mas/swtools"
export XDCBUILDCFG="${SWTOOLS_PATH}/config.bld"

PATH_append = ":${XDC_INSTALL_DIR}"

XDCPATH.="${XDCCGROOT}/include;${XDC_INSTALL_DIR}/packages;${SYSBIOS_INSTALL_DIR}/packages;"
XDCPATH.="${SWTOOLS_INSTALL_DIR};${MATHLIB_WORK_DIR};"

export XDCPATH

MATHLIB_PACKAGE_NAME = "mathlib_${MATHLIB_BUILD_TARGET}_${PV}"

do_compile() {
	cd ${MATHLIB_WORK_DIR}/ti/mathlib;
	xdc XDCARGS="${MATHLIB_BUILD_TARGET} bundle" XDCOPTIONS="-v"
}

do_install() {
	install -d ${D}${MATHLIB_INSTALL_DIR_RECIPE}

	find -name "${MATHLIB_PACKAGE_NAME}.zip" -exec unzip -o {} -d ${D}${MATHLIB_INSTALL_DIR_RECIPE} \;
	mv ${D}${MATHLIB_INSTALL_DIR_RECIPE}/mathlib/* ${D}${MATHLIB_INSTALL_DIR_RECIPE}
	rm -r ${D}${MATHLIB_INSTALL_DIR_RECIPE}/mathlib
}

inherit deploy
addtask deploy before do_build after do_install

do_deploy() {
	install -d ${DEPLOYDIR}
	# zip up files in ${D}${MATHLIB_INSTALL_DIR_RECIPE}
	# and place the zip file in ${DEPLOYDIR}
	mkdir ./${MATHLIB_PACKAGE_NAME}
	cp -r ${D}${MATHLIB_INSTALL_DIR_RECIPE}/* ./${MATHLIB_PACKAGE_NAME}
	zip -9 -y -r -q ${DEPLOYDIR}/${MATHLIB_PACKAGE_NAME}.zip ./${MATHLIB_PACKAGE_NAME}
	rm -r ./${MATHLIB_PACKAGE_NAME}
}

ALLOW_EMPTY_${PN} = "1"
FILES_${PN}-dev += "${MATHLIB_INSTALL_DIR_RECIPE}"

INSANE_SKIP_${PN}-dev = "arch"
