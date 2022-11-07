DESCRIPTION = "Userspace libraries for PowerVR Rogue GPU on TI SoCs"
HOMEPAGE = "http://git.ti.com/graphics/ti-img-rogue-umlibs"
LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7232b98c1c58f99e3baa03de5207e76f"

inherit features_check

REQUIRED_MACHINE_FEATURES = "gpu"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "j7-evm|j7-hs-evm|j721s2-evm|j721s2-hs-evm|j784s4-evm|j784s4-hs-evm|am62xx"

PR = "r2"

BRANCH = "linuxws/dunfell/k5.10/${PV}_unified_fw_pagesize"

SRC_URI = "git://git.ti.com/git/graphics/ti-img-rogue-umlibs.git;protocol=https;branch=${BRANCH}"
SRCREV = "5977e82b96028f783d39c7219f016c1faf8dc5f5"

TARGET_PRODUCT_j7-evm = "j721e_linux"
TARGET_PRODUCT_j7-hs-evm = "j721e_linux"
TARGET_PRODUCT_j721s2-evm = "j721s2_linux"
TARGET_PRODUCT_j721s2-hs-evm = "j721s2_linux"
TARGET_PRODUCT_j784s4-evm = "j784s4_linux"
TARGET_PRODUCT_j784s4-hs-evm = "j784s4_linux"
TARGET_PRODUCT_am62xx = "am62_linux"
PVR_BUILD ?= "release"
PVR_WS = "wayland"

INITSCRIPT_NAME = "rc.pvr"
INITSCRIPT_PARAMS = "defaults 8"

inherit update-rc.d

PROVIDES += "virtual/egl virtual/libgles1 virtual/libgles2 virtual/libgbm"

DEPENDS += "libdrm wayland expat"
RDEPENDS_${PN} += "bash"
RDEPENDS_${PN} += "wayland expat"

RPROVIDES_${PN} = "libegl libgles1 libgles2 libgbm"
RPROVIDES_${PN}-dev = "libegl-dev libgles1-dev libgles2-dev libgbm-dev"
RPROVIDES_${PN}-dbg = "libegl-dbg libgles1-dbg libgles2-dbg"

RREPLACES_${PN} = "libegl libgles1 liblges2 libgbm"
RREPLACES_${PN}-dev = "libegl-dev libgles1-dev libgles2-dev libgbm-dev"
RREPLACES_${PN}-dbg = "libegl-dbg libgles1-dbg libgles2-dbg"

RCONFLICTS_${PN} = "libegl libgles1 libgles2 libgbm"
RCONFLICTS_${PN}-dev = "libegl-dev libgles1-dev libgles2-dev libgbm-dev"
RCONFLICTS_${PN}-dbg = "libegl-dbg libgles1-dbg libgles2-dbg"

RRECOMMENDS_${PN} += "ti-img-rogue-driver"

S = "${WORKDIR}/git"

do_install () {
    oe_runmake install DESTDIR=${D} TARGET_PRODUCT=${TARGET_PRODUCT} BUILD=${PVR_BUILD} WINDOW_SYSTEM=${PVR_WS}
    chown -R root:root ${D}
}

FILES_${PN} += " ${base_libdir}/firmware/"
FILES_${PN} += " ${datadir}/"

PACKAGES =+ "${PN}-plugins"
FILES_${PN}-plugins = "${libdir}/libGLESv2.so ${libdir}/libGLESv1_CM.so ${libdir}/libEGL.so ${libdir}/dri/pvr_dri.so"
RDEPENDS_${PN} += "${PN}-plugins"

ALLOW_EMPTY_${PN}-plugins = "1"

INSANE_SKIP_${PN} += "ldflags arch already-stripped"
INSANE_SKIP_${PN}-plugins = "dev-so"

CLEANBROKEN = "1"
