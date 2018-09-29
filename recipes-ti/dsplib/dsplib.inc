SUMMARY = "DSPLIB contains optimized signal processing functions for TI DSPs"
HOMEPAGE = "http://git.ti.com/ep-processor-libraries/dsplib"
LICENSE = "BSD-3-Clause"

require ../includes/ti-paths.inc

PV = "3_4_0_1"
PR = "r0"

DEPENDS = "ti-cgt6x-native \
           ti-xdctools-native \
           ti-sysbios \
           doxygen-native \
           zip-native \
           swtools"

S = "${WORKDIR}/git"

SRC_URI = "git://git.ti.com/ep-processor-libraries/dsplib.git;protocol=git;branch=master"
SRCREV = "bf5a51cba462c5cb03ed20a6a1bb1bd16c0683e1"

LIC_FILES_CHKSUM = "file://ti/dsplib/src/common/c66/lnk.cmd;beginline=1;endline=37;md5=0f0c9c4d51d8af75419781591ace190b"

export DSPLIB_WORK_DIR="${S}"
export CGTOOLS_INSTALL_DIR  = "${STAGING_DIR_NATIVE}/usr/share/ti/cgt-c6x"
export C64PCODEGENTOOL="${CGTOOLS_INSTALL_DIR}"
export C674CODEGENTOOL="${CGTOOLS_INSTALL_DIR}"
export C66CODEGENTOOL ="${CGTOOLS_INSTALL_DIR}"
export SWTOOLS_PATH = "${SWTOOLS_INSTALL_DIR}/ti/mas/swtools"
export XDCBUILDCFG="${SWTOOLS_PATH}/config.bld"
export INCDIR="${MATHLIB_INSTALL_DIR}/"

PATH_append = ":${XDC_INSTALL_DIR}"

XDCPATH.="${XDCCGROOT}/include;${XDC_INSTALL_DIR}/packages;${SYSBIOS_INSTALL_DIR}/packages;"
XDCPATH.="${SWTOOLS_INSTALL_DIR};${MATHLIB_INSTALL_DIR};${DSPLIB_WORK_DIR};"

export XDCPATH

DSPLIB_PACKAGE_NAME = "dsplib_${DSPLIB_BUILD_TARGET}_${PV}"

do_compile() {
	cd ${DSPLIB_WORK_DIR}/ti/dsplib;
	xdc XDCARGS="${DSPLIB_BUILD_TARGET} bundle" XDCOPTIONS="-v"
}

do_install() {
	install -d ${D}${DSPLIB_INSTALL_DIR_RECIPE}

	find -name "${DSPLIB_PACKAGE_NAME}.zip" -exec unzip -o {} -d ${D}${DSPLIB_INSTALL_DIR_RECIPE} \;
	mv ${D}${DSPLIB_INSTALL_DIR_RECIPE}/dsplib/* ${D}${DSPLIB_INSTALL_DIR_RECIPE}
	rm -r ${D}${DSPLIB_INSTALL_DIR_RECIPE}/dsplib
}

inherit deploy
addtask deploy before do_build after do_install

do_deploy() {
	install -d ${DEPLOYDIR}
	# zip up files in ${D}${DSPLIB_INSTALL_DIR_RECIPE}
	# and place the zip file in ${DEPLOYDIR}
	mkdir ./${DSPLIB_PACKAGE_NAME}
	cp -r ${D}${DSPLIB_INSTALL_DIR_RECIPE}/* ./${DSPLIB_PACKAGE_NAME}
	zip -9 -y -r -q ${DEPLOYDIR}/${DSPLIB_PACKAGE_NAME}.zip ./${DSPLIB_PACKAGE_NAME}
	rm -r ./${DSPLIB_PACKAGE_NAME}
}

ALLOW_EMPTY_${PN} = "1"
FILES_${PN}-dev += "${DSPLIB_INSTALL_DIR_RECIPE}"

INSANE_SKIP_${PN}-dev = "arch"
