DESCRIPTION = "Userspace libraries for PowerVR Rogue GPU on TI SoCs"
HOMEPAGE = "http://git.ti.com/graphics/ti-img-rogue-umlibs"
LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7232b98c1c58f99e3baa03de5207e76f"

inherit features_check

REQUIRED_MACHINE_FEATURES = "gpu"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "j721e|j721s2|am62xx"

PR = "r2"

BRANCH = "linuxws/dunfell/k5.10/${PV}_unified_fw_pagesize"

SRC_URI = "git://git.ti.com/git/graphics/ti-img-rogue-umlibs.git;protocol=https;branch=${BRANCH}"
SRCREV = "fba0c770b712640ab3761dbe8369d43f89f616ed"

TARGET_PRODUCT:j721e = "j721e_linux"
TARGET_PRODUCT:j721s2 = "j721s2_linux"
TARGET_PRODUCT:am62xx = "am62_linux"
PVR_BUILD ?= "release"
PVR_WS = "wayland"

INITSCRIPT_NAME = "rc.pvr"
INITSCRIPT_PARAMS = "defaults 8"

inherit update-rc.d

PROVIDES += "virtual/egl virtual/libgles1 virtual/libgles2 virtual/libgbm"

DEPENDS += "libdrm wayland expat"
RDEPENDS:${PN} += "bash"
RDEPENDS:${PN} += "wayland expat"

RPROVIDES:${PN} = "libegl libgles1 libgles2 libgbm"
RPROVIDES:${PN}-dev = "libegl-dev libgles1-dev libgles2-dev libgbm-dev"
RPROVIDES:${PN}-dbg = "libegl-dbg libgles1-dbg libgles2-dbg"

RREPLACES:${PN} = "libegl libgles1 liblges2 libgbm"
RREPLACES:${PN}-dev = "libegl-dev libgles1-dev libgles2-dev libgbm-dev"
RREPLACES:${PN}-dbg = "libegl-dbg libgles1-dbg libgles2-dbg"

RCONFLICTS:${PN} = "libegl libgles1 libgles2 libgbm"
RCONFLICTS:${PN}-dev = "libegl-dev libgles1-dev libgles2-dev libgbm-dev"
RCONFLICTS:${PN}-dbg = "libegl-dbg libgles1-dbg libgles2-dbg"

RRECOMMENDS:${PN} += "ti-img-rogue-driver"

S = "${WORKDIR}/git"

do_install () {
    oe_runmake install DESTDIR=${D} TARGET_PRODUCT=${TARGET_PRODUCT} BUILD=${PVR_BUILD} WINDOW_SYSTEM=${PVR_WS}
    chown -R root:root ${D}
}

FILES:${PN} += " ${nonarch_base_libdir}/firmware/"
FILES:${PN} += " ${datadir}/"

PACKAGES =+ "${PN}-plugins"
FILES:${PN}-plugins = "${libdir}/libGLESv2.so ${libdir}/libGLESv1_CM.so ${libdir}/libEGL.so ${libdir}/dri/pvr_dri.so"
RDEPENDS:${PN} += "${PN}-plugins"

ALLOW_EMPTY:${PN}-plugins = "1"

INSANE_SKIP:${PN} += "ldflags arch already-stripped"
INSANE_SKIP:${PN}-plugins = "dev-so"

CLEANBROKEN = "1"
