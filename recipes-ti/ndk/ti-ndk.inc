SUMMARY = "TI RTOS_Networking"
HOMEPAGE = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/ndk/index.html"
SECTION = "devel"
LICENSE = "BSD"

require recipes-ti/includes/ti-paths.inc

S = "${WORKDIR}/ndk_${PV}"
SRC_URI = "http://software-dl.ti.com/targetcontent/ndk/${PV}/exports/ndk_${PV}.zip;name=ndkzip"

do_install() {
    CP_ARGS="-Prf --preserve=mode,timestamps --no-preserve=ownership"
    install -d ${D}${NDK_INSTALL_DIR_RECIPE}
    cp ${CP_ARGS} ${S}/* ${D}${NDK_INSTALL_DIR_RECIPE}
}

ALLOW_EMPTY:${PN} = "1"
FILES:${PN}-dev += "${NDK_INSTALL_DIR_RECIPE}"

INSANE_SKIP:${PN}-dev = "arch ldflags staticdev file-rdeps"
