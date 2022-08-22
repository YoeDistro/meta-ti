DESCRIPTION = "Userspace libraries for PowerVR SGX chipset on TI SoCs"
HOMEPAGE = "https://git.ti.com/graphics/omap5-sgx-ddk-um-linux"
LICENSE = "TI-TSPA"
LIC_FILES_CHKSUM = "file://TI-Linux-Graphics-DDK-UM-Manifest.doc;md5=b17390502bc89535c86cfbbae961a2a8"

inherit features_check

REQUIRED_MACHINE_FEATURES = "gpu"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "pandaboard|beagleboard|ti33x|ti43x|omap-a15|k3"

PR = "r37"

BRANCH = "ti-img-sgx/dunfell/${PV}"

SRC_URI = "git://git.ti.com/git/graphics/omap5-sgx-ddk-um-linux.git;protocol=https;branch=${BRANCH}"
SRCREV = "742cf38aba13e1ba1a910cf1f036a1a212c263b6"

TARGET_PRODUCT_omap-a15 = "jacinto6evm"
TARGET_PRODUCT_ti33x = "ti335x"
TARGET_PRODUCT_ti43x = "ti437x"
TARGET_PRODUCT_k3 = "ti654x"
TARGET_PRODUCT_beagleboard = "ti343x"
TARGET_PRODUCT_pandaboard = "ti443x"

INITSCRIPT_NAME = "rc.pvr"
INITSCRIPT_PARAMS = "defaults 8"

inherit update-rc.d

PROVIDES += "virtual/egl virtual/libgles1 virtual/libgles2 virtual/libgbm"

DEPENDS += "libdrm udev wayland wayland-protocols libffi expat"
RDEPENDS_${PN} += "libdrm libdrm-omap udev wayland wayland-protocols libffi expat"

RPROVIDES_${PN} = "libegl libgles1 libgles2 libgbm"
RPROVIDES_${PN}-dev = "libegl-dev libgles1-dev libgles2-dev libgbm-dev"
RPROVIDES_${PN}-dbg = "libegl-dbg libgles1-dbg libgles2-dbg libgbm-dbg"

RREPLACES_${PN} = "libegl libgles1 libgles2 libgbm"
RREPLACES_${PN}-dev = "libegl-dev libgles1-dev libgles2-dev libgbm-dev"
RREPLACES_${PN}-dbg = "libegl-dbg libgles1-dbg libgles2-dbg libgbm-dbg"

RCONFLICTS_${PN} = "libegl libgles1 libgles2 libgbm"
RCONFLICTS_${PN}-dev = "libegl-dev libgles1-dev libgles2-dev libgbm-dev"
RCONFLICTS_${PN}-dbg = "libegl-dbg libgles1-dbg libgles2-dbg libgbm-dbg"

# The actual SONAME is libGLESv2.so.2, so need to explicitly specify RPROVIDES for .so.1 here
RPROVIDES_${PN} += "libGLESv2.so.1"

RRECOMMENDS_${PN} += "ti-sgx-ddk-km"

S = "${WORKDIR}/git"

do_install () {
    oe_runmake install DESTDIR=${D} TARGET_PRODUCT=${TARGET_PRODUCT}
    ln -sf libGLESv2.so.2 ${D}${libdir}/libGLESv2.so.1

    chown -R root:root ${D}
}

FILES_${PN} =  "${bindir}/*"
FILES_${PN} += " ${libdir}/*"
FILES_${PN} +=  "${includedir}/*"
FILES_${PN} +=  "${sysconfdir}/*"

INSANE_SKIP_${PN} += "dev-so ldflags useless-rpaths"
INSANE_SKIP_${PN} += "already-stripped dev-deps"

CLEANBROKEN = "1"
