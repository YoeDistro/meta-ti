SUMMARY = "MATHLIB contains optimized floating-point math functions for TI DSPs"
HOMEPAGE = "http://git.ti.com/ep-processor-libraries/mathlib"
LICENSE = "BSD-3-Clause"

require ../includes/ti-paths.inc

PV = "3_1_2_4"
PR = "r2"

DEPENDS = "ti-cgt6x-native \
           ti-xdctools-native \
           ti-sysbios \
           doxygen-native \
           swtools"

S = "${WORKDIR}/git"

MATHLIB_GIT_BRANCH = "master"
MATHLIB_GIT_PROTOCOL = "https"
MATHLIB_GIT_URI = "git://git.ti.com/git/ep-processor-libraries/mathlib.git"
MATHLIB_SRCREV = "eadcc9547faf69789661b004bb0b5ef53bb69951"

SRC_URI = "${MATHLIB_GIT_URI};protocol=${MATHLIB_GIT_PROTOCOL};branch=${MATHLIB_GIT_BRANCH}"
SRCREV = "${MATHLIB_SRCREV}"

LIC_FILES_CHKSUM = "file://ti/mathlib/src/acosdp/acosdp.h;beginline=1;endline=35;md5=c133f7e139b46385906c861dd9552250"

export MATHLIB_WORK_DIR = "${S}"
export CGTOOLS_INSTALL_DIR = "${STAGING_DIR_NATIVE}/usr/share/ti/cgt-c6x"
export C64PCODEGENTOOL = "${CGTOOLS_INSTALL_DIR}"
export C674CODEGENTOOL = "${CGTOOLS_INSTALL_DIR}"
export C66CODEGENTOOL = "${CGTOOLS_INSTALL_DIR}"
export SWTOOLS_PATH = "${SWTOOLS_INSTALL_DIR}/ti/mas/swtools"
export XDCBUILDCFG = "${SWTOOLS_PATH}/config.bld"

PATH:append = ":${XDC_INSTALL_DIR}"

XDCPATH .= "${XDCCGROOT}/include;${XDC_INSTALL_DIR}/packages;${SYSBIOS_INSTALL_DIR}/packages;"
XDCPATH .= "${SWTOOLS_INSTALL_DIR};${MATHLIB_WORK_DIR};"

export XDCPATH

MATHLIB_PACKAGE_NAME = "mathlib_${MATHLIB_BUILD_TARGET}_${PV}"

do_compile() {
	cd ${MATHLIB_WORK_DIR}/ti/mathlib;
	xdc XDCARGS="${MATHLIB_BUILD_TARGET} bundle" XDCOPTIONS="-v"
}

do_install() {
	install -d ${D}${MATHLIB_INSTALL_DIR_RECIPE}

	find -name "${MATHLIB_PACKAGE_NAME}.zip" -exec unzip -o {} -d ${D}${MATHLIB_INSTALL_DIR_RECIPE} \;
	cp -r ${D}${MATHLIB_INSTALL_DIR_RECIPE}/mathlib/. ${D}${MATHLIB_INSTALL_DIR_RECIPE}
	rm -r ${D}${MATHLIB_INSTALL_DIR_RECIPE}/mathlib
}

ALLOW_EMPTY:${PN} = "1"
FILES:${PN}-dev += "${MATHLIB_INSTALL_DIR_RECIPE}"

INSANE_SKIP:${PN}-dev = "arch"
COMPATIBLE_HOST ?= "null"
COMPATIBLE_HOST:ti-soc = "(.*)"
