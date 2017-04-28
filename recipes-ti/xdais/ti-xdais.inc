DESCRIPTION = "TI eXpress DSP Algorithm Interface Standard (XDAIS) specification"
HOMEPAGE = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/xdais"
LICENSE = "GPLv2"
SECTION = "devel"

require ../includes/ti-paths.inc
require ../includes/ti-staging.inc

S = "${WORKDIR}/xdais_${PV}"

SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/xdais/${PV}/exports/xdais_${PV}.tar.gz;name=xdaistarball"

do_install() {
    CP_ARGS="-Prf --preserve=mode,timestamps --no-preserve=ownership"
    install -d ${D}${XDAIS_INSTALL_DIR_RECIPE}
    cp ${CP_ARGS} ${S}/* ${D}${XDAIS_INSTALL_DIR_RECIPE}
}

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INSANE_SKIP_${PN}-dev = "arch"

ALLOW_EMPTY_${PN} = "1"
FILES_${PN}-dev += "${XDAIS_INSTALL_DIR_RECIPE}"
