DESCRIPTION = "Userspace libraries for PowerVR SGX chipset on TI SoCs"
HOMEPAGE = "https://git.ti.com/graphics/omap5-sgx-ddk-um-linux"
LICENSE = "TI-TSPA"
LIC_FILES_CHKSUM = "file://TI-Linux-Graphics-DDK-UM-Manifest.doc;md5=b17390502bc89535c86cfbbae961a2a8"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "ti33x|ti43x|omap-a15|k3"

PR = "r34"

BRANCH = "ti-img-sgx/thud/${PV}"

SRC_URI = "git://git.ti.com/graphics/omap5-sgx-ddk-um-linux.git;protocol=git;branch=${BRANCH}"
SRCREV = "87d7e5c1e4db1bab048939c9719059d549c1e8dd"

TARGET_PRODUCT_omap-a15 = "jacinto6evm"
TARGET_PRODUCT_ti33x = "ti335x"
TARGET_PRODUCT_ti43x = "ti437x"
TARGET_PRODUCT_k3 = "ti654x"

INITSCRIPT_NAME = "rc.pvr"
INITSCRIPT_PARAMS = "defaults 8"

inherit update-rc.d

PROVIDES += "virtual/egl virtual/libgles1 virtual/libgles2 virtual/libgbm omap5-sgx-ddk-um-linux"

DEPENDS += "libdrm udev wayland wayland-protocols libffi expat"
RDEPENDS_${PN} += "libdrm libdrm-omap udev wayland wayland-protocols libffi expat"

RPROVIDES_${PN} = "libegl libgles1 libgles2 libgbm omap5-sgx-ddk-um-linux"
RPROVIDES_${PN}-dev = "libegl-dev libgles1-dev libgles2-dev libgbm-dev omap5-sgx-ddk-um-linux-dev"
RPROVIDES_${PN}-dbg = "libegl-dbg libgles1-dbg libgles2-dbg libgbm-dbg omap5-sgx-ddk-um-linux-dbg"

RREPLACES_${PN} = "libegl libgles1 libgles2 libgbm omap5-sgx-ddk-um-linux"
RREPLACES_${PN}-dev = "libegl-dev libgles1-dev libgles2-dev libgbm-dev omap5-sgx-ddk-um-linux-dev"
RREPLACES_${PN}-dbg = "libegl-dbg libgles1-dbg libgles2-dbg libgbm-dbg omap5-sgx-ddk-um-linux-dbg"

RCONFLICTS_${PN} = "libegl libgles1 libgles2 libgbm omap5-sgx-ddk-um-linux"
RCONFLICTS_${PN}-dev = "libegl-dev libgles1-dev libgles2-dev libgbm-dev omap5-sgx-ddk-um-linux-dev"
RCONFLICTS_${PN}-dbg = "libegl-dbg libgles1-dbg libgles2-dbg libgbm-dbg omap5-sgx-ddk-um-linux-dbg"

# The actual SONAME is libGLESv2.so.2, so need to explicitly specify RPROVIDES for .so.1 here
RPROVIDES_${PN} += "libGLESv2.so.1"

S = "${WORKDIR}/git"

do_install () {
    oe_runmake install DESTDIR=${D} TARGET_PRODUCT=${TARGET_PRODUCT}
    ln -sf libGLESv2.so.${PV} ${D}${libdir}/libGLESv2.so.1

    rm -f ${D}${libdir}/pkgconfig/wayland-egl.pc

    chown -R root:root ${D}
}

FILES_${PN} =  "${bindir}/*"
FILES_${PN} += " ${libdir}/*"
FILES_${PN} +=  "${includedir}/*"
FILES_${PN} +=  "${sysconfdir}/*"

PACKAGES =+ "${PN}-plugins"
FILES_${PN}-plugins = "${libdir}/libsrv_init.so ${libdir}/libsrv_um.so ${libdir}/libglslcompiler.so ${libdir}/libPVRScopeServices.so ${libdir}/libGLESv2.so ${libdir}/libEGL.so ${libdir}/libGLES_CM.so ${libdir}/libpvrDRMWSEGL.so  ${libdir}/libpvrGBMWSEGL.so  ${libdir}/libpvrws_WAYLAND.so"
RDEPENDS_${PN} += "${PN}-plugins"

ALLOW_EMPTY_${PN}-plugins = "1"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"

INSANE_SKIP_${PN} += "dev-so ldflags useless-rpaths"
INSANE_SKIP_${PN}-plugins = "dev-so"
INSANE_SKIP_${PN} += "already-stripped"

CLEANBROKEN = "1"
