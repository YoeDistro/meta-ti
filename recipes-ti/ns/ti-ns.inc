SUMMARY = "TI Network Services"
HOMEPAGE = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/ns/index.html"
SECTION = "devel"
LICENSE = "BSD"

require recipes-ti/includes/ti-paths.inc

S = "${WORKDIR}/ns_${PV}"

SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/ns/${PV}/exports/ns_${PV}.zip;name=nszip"

do_install() {
    CP_ARGS="-Prf --preserve=mode,timestamps --no-preserve=ownership"
    install -d ${D}${NS_INSTALL_DIR_RECIPE}
    cp ${CP_ARGS} ${S}/* ${D}${NS_INSTALL_DIR_RECIPE}
}

ALLOW_EMPTY_${PN} = "1"
FILES_${PN}-dev += "${NS_INSTALL_DIR_RECIPE}"

INSANE_SKIP_${PN}-dev = "arch ldflags staticdev file-rdeps"
