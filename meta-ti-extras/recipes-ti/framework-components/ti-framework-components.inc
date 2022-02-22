DESCRIPTION = "TI Framework Components"
HOMEPAGE = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/fc"
SECTION = "devel"
LICENSE = "BSD-3-Clause"

require recipes-ti/includes/ti-paths.inc

S = "${WORKDIR}/framework_components_${PV}"

SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/fc/${PV}/exports/framework_components_${PV},lite.tar.gz;name=fctarball"

do_install() {
    CP_ARGS="-Prf --preserve=mode,timestamps --no-preserve=ownership"
    install -d ${D}${FC_INSTALL_DIR_RECIPE}
    cp ${CP_ARGS} ${S}/* ${D}${FC_INSTALL_DIR_RECIPE}
}

INSANE_SKIP:${PN} = "ldflags"

ALLOW_EMPTY:${PN} = "1"
FILES:${PN}-dev += "${FC_INSTALL_DIR_RECIPE}"
