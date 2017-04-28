DESCRIPTION = "DSPLIB contains optimized general-purpose signal-processing routines for TI DSPs"
HOMEPAGE = "http://www.ti.com/tool/sprc265"
SECTION = "devel"
LICENSE = "BSD-3-Clause"

require ../includes/ti-paths.inc
require ../includes/ti-staging.inc
require ../includes/ti-unpack.inc

COMPATIBLE_MACHINE = "omap-a15|keystone"

SRC_URI = "http://software-dl.ti.com/sdoemb/sdoemb_public_sw/dsplib/${PV}/exports/${BINFILE};name=dsplib"

BINFILE = "dsplib_c66x_${PV}_Linux.bin"
TI_BIN_UNPK_ARGS = "--mode silent --prefix ${S}"
TI_BIN_UNPK_CMDS = ""

do_install() {
    CP_ARGS="-Prf --preserve=mode,timestamps --no-preserve=ownership"
    install -d ${D}${DSPLIB_C66_INSTALL_DIR_RECIPE}
    cp ${CP_ARGS} ${S}/* ${D}${DSPLIB_C66_INSTALL_DIR_RECIPE}
}

ALLOW_EMPTY_${PN} = "1"
FILES_${PN}-dev += "${DSPLIB_C66_INSTALL_DIR_RECIPE}"

INSANE_SKIP_${PN}-dev = "arch"
