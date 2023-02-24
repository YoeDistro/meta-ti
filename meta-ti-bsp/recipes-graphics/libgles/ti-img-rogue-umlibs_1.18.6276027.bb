DESCRIPTION = "Userspace libraries for PowerVR Rogue GPU on TI SoCs"
HOMEPAGE = "http://git.ti.com/graphics/ti-img-rogue-umlibs"
LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE;md5=7232b98c1c58f99e3baa03de5207e76f"

inherit bin_package

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "j721e|j721s2|j784s4|am62xx"

PR = "r2"

BRANCH = "linuxws/kirkstone/k5.10/${PV}"
SRC_URI = "git://git.ti.com/git/graphics/ti-img-rogue-umlibs.git;protocol=https;branch=${BRANCH}"
SRCREV = "51e598919641d51156a631efa5447124a3c0f543"
S = "${WORKDIR}/git/targetfs/${TARGET_PRODUCT}/${PVR_WS}/${PVR_BUILD}"

TARGET_PRODUCT:j721e = "j721e_linux"
TARGET_PRODUCT:j721s2 = "j721s2_linux"
TARGET_PRODUCT:j784s4 = "j784s4_linux"
TARGET_PRODUCT:am62xx = "am62_linux"
PVR_BUILD = "release"
PVR_WS = "lws-generic"

RDEPENDS:${PN} += "mesa-megadriver libdrm ti-img-rogue-driver"

do_install:append() {
    rm -rf "${D}/etc/init.d"
    rm -rf "${D}/usr/lib/libvulkan.so"
    rm -rf "${D}/usr/lib/libvulkan.so.0"
    rm -rf "${D}/usr/lib/libvulkan.so.1"
}

PACKAGES = "${PN}-tools ${PN}"
FILES:${PN}-tools = "${bindir}/"
RDEPENDS:${PN}-tools = "python3-core"
RRECOMMENDS:${PN} += "${PN}-tools"

INSANE_SKIP:${PN} += "ldflags arch already-stripped dev-so"
