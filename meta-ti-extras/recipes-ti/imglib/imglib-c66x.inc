SUMMARY = "IMGLIB for TI DSPs"
DESCRIPTION = "IMGLIB contains optimized general-purpose image/video processing routines for TI DSPs"
HOMEPAGE = "http://www.ti.com/tool/sprc264"
SECTION = "devel"
LICENSE = "BSD-3-Clause"

require ../includes/ti-paths.inc
require ../includes/ti-staging.inc
require ../includes/ti-unpack.inc

COMPATIBLE_MACHINE = "omap-a15"

SRC_URI = "http://software-dl.ti.com/sdoemb/sdoemb_public_sw/imglib/${PV}/exports/${BINFILE};name=imglib"

S = "${UNPACKDIR}/imglib-c66x-${PV}"

BINFILE = "imglib_c66x_${PV}_Linux.bin"
TI_BIN_UNPK_ARGS = "--mode silent --prefix ${S}"
TI_BIN_UNPK_CMDS = ""

do_install() {
    CP_ARGS="-Prf --preserve=mode,timestamps --no-preserve=ownership"
    install -d ${D}${IMGLIB_C66_INSTALL_DIR_RECIPE}
    cp ${CP_ARGS} ${S}/* ${D}${IMGLIB_C66_INSTALL_DIR_RECIPE}
}

ALLOW_EMPTY:${PN} = "1"
FILES:${PN}-dev += "${IMGLIB_C66_INSTALL_DIR_RECIPE}"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

RDEPENDS:${PN}-dev += "perl"
INSANE_SKIP:${PN}-dev = "arch"
