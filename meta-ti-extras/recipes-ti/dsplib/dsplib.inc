SUMMARY = "DSPLIB contains optimized signal processing functions for TI DSPs"
HOMEPAGE = "http://git.ti.com/ep-processor-libraries/dsplib"
LICENSE = "BSD-3-Clause"

require ../includes/ti-paths.inc

PV = "3_4_0_4"
PR = "r2"

DEPENDS = "ti-cgt6x-native \
           ti-xdctools-native \
           ti-sysbios \
           doxygen-native \
           swtools"

DSPLIB_GIT_BRANCH = "master"
DSPLIB_GIT_PROTOCOL = "https"
DSPLIB_GIT_URI = "git://git.ti.com/git/ep-processor-libraries/dsplib.git"
DSPLIB_SRCREV = "0390b171042c602e07669fd28a371cc319b90161"

SRC_URI = "${DSPLIB_GIT_URI};protocol=${DSPLIB_GIT_PROTOCOL};branch=${DSPLIB_GIT_BRANCH}"
SRCREV = "${DSPLIB_SRCREV}"

LIC_FILES_CHKSUM = "file://ti/dsplib/src/common/c66/lnk.cmd;beginline=1;endline=37;md5=0f0c9c4d51d8af75419781591ace190b"

export DSPLIB_WORK_DIR = "${S}"
export CGTOOLS_INSTALL_DIR = "${STAGING_DIR_NATIVE}/usr/share/ti/cgt-c6x"
export C64PCODEGENTOOL = "${CGTOOLS_INSTALL_DIR}"
export C674CODEGENTOOL = "${CGTOOLS_INSTALL_DIR}"
export C66CODEGENTOOL = "${CGTOOLS_INSTALL_DIR}"
export SWTOOLS_PATH = "${SWTOOLS_INSTALL_DIR}/ti/mas/swtools"
export XDCBUILDCFG = "${SWTOOLS_PATH}/config.bld"
export INCDIR = "${MATHLIB_INSTALL_DIR}/"

PATH:append = ":${XDC_INSTALL_DIR}"

XDCPATH .= "${XDCCGROOT}/include;${XDC_INSTALL_DIR}/packages;${SYSBIOS_INSTALL_DIR}/packages;"
XDCPATH .= "${SWTOOLS_INSTALL_DIR};${MATHLIB_INSTALL_DIR};${DSPLIB_WORK_DIR};"

export XDCPATH

DSPLIB_PACKAGE_NAME = "dsplib_${DSPLIB_BUILD_TARGET}_${PV}"

do_compile() {
	cd ${DSPLIB_WORK_DIR}/ti/dsplib;
	xdc XDCARGS="${DSPLIB_BUILD_TARGET} bundle" XDCOPTIONS="-v"
}

do_install() {
	install -d ${D}${DSPLIB_INSTALL_DIR_RECIPE}

	find -name "${DSPLIB_PACKAGE_NAME}.zip" -exec unzip -o {} -d ${D}${DSPLIB_INSTALL_DIR_RECIPE} \;
	cp -r ${D}${DSPLIB_INSTALL_DIR_RECIPE}/dsplib/. ${D}${DSPLIB_INSTALL_DIR_RECIPE}
	rm -r ${D}${DSPLIB_INSTALL_DIR_RECIPE}/dsplib
}

ALLOW_EMPTY:${PN} = "1"
FILES:${PN}-dev += "${DSPLIB_INSTALL_DIR_RECIPE}"

INSANE_SKIP:${PN}-dev = "arch"
COMPATIBLE_HOST ?= "null"
COMPATIBLE_HOST:ti-soc = "(.*)"
