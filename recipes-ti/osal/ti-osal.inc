DESCRIPTION = "TI Operating System Abstraction Library (OSAL)"
HOMEPAGE = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/osal"
LICENSE = "BSD & EPL-1.0"
SECTION = "devel"

require ../includes/ti-paths.inc
require ../includes/ti-staging.inc

S = "${WORKDIR}/osal_${PV}"

SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/osal/${PV}/exports/osal_${PV}.tar.gz;name=osaltarball"

do_install() {
    install -d ${D}${OSAL_INSTALL_DIR_RECIPE}
    cp -pPrf ${S}/* ${D}${OSAL_INSTALL_DIR_RECIPE}
}

INSANE_SKIP_${PN}-dev = "staticdev"

ALLOW_EMPTY_${PN} = "1"
FILES_${PN}-dev += "${OSAL_INSTALL_DIR_RECIPE}"

COMPATIBLE_HOST ?= "null"
COMPATIBLE_HOST_ti-soc = "(.*)"
