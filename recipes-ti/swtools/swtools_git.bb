SUMMARY = "TI Software Tools"
HOMEPAGE = "https://git.ti.com/ep-processor-libraries/swtools"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://ti/mas/swtools/copyright.txt;md5=b1f52a1435051fdb18f8885b0384147d"

require ../includes/ti-paths.inc

DEPENDS = "ti-cgt6x-native \
           ti-sysbios \
           ti-xdctools-native"

PV = "5_0_8"
PR = "r3"
S = "${WORKDIR}/git"

SWTOOLS_GIT_BRANCH = "master"
SWTOOLS_GIT_PROTOCOL = "https"
SWTOOLS_GIT_URI = "git://git.ti.com/git/ep-processor-libraries/swtools.git"
SWTOOLS_SRCREV = "7d1c85e686b1466cd2d535fc9cac5baf1aae24a4"

SRC_URI = "${SWTOOLS_GIT_URI};protocol=${SWTOOLS_GIT_PROTOCOL};branch=${SWTOOLS_GIT_BRANCH}"
SRCREV = "${SWTOOLS_SRCREV}"


export C64PCODEGENTOOL = "${CGTOOLS_INSTALL_DIR}"
export C674CODEGENTOOL = "${CGTOOLS_INSTALL_DIR}"
export C66CODEGENTOOL  = "${CGTOOLS_INSTALL_DIR}"

PATH_append = ":${XDC_INSTALL_DIR}"

XDCPATH .= "${XDCCGROOT}/include;${XDC_INSTALL_DIR}/packages;${SYSBIOS_INSTALL_DIR}/packages;"

export XDCPATH

do_compile() {
	cd ${S}/ti/mas/swtools
	xdc
}

do_install() {
	CP_ARGS="-Prf --preserve=mode,timestamps --no-preserve=ownership"
	install -d ${D}${SWTOOLS_INSTALL_DIR_RECIPE}
	cp ${CP_ARGS} ${S}/* ${D}${SWTOOLS_INSTALL_DIR_RECIPE}
}

FILES_${PN}-dev += "${SWTOOLS_INSTALL_DIR_RECIPE}"

INSANE_SKIP_${PN}-dev = "arch staticdev"
ALLOW_EMPTY_${PN} = "1"
COMPATIBLE_HOST ?= "null"
COMPATIBLE_HOST_ti-soc = "(.*)"
