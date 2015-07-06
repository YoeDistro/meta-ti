DESCRIPTION = "Userspace libraries for SGX"
HOMEPAGE = "http://downloads.ti.com/dsps/dsps_public_sw/gfxsdk"
LICENSE = "TI-TSPA"
LIC_FILES_CHKSUM = "file://OMAP5-Linux-Graphics-DDK-UM-Manifest.doc;md5=360d293df455e4f2d363bb4014a49603"

SRC_URI = "git://git.ti.com/graphics/omap5-sgx-ddk-um-linux.git;protocol=git"
SRCREV = "b6e57ccf36fc8c36d988246bc7510f0dec42d991"

PR = "r7"
PROVIDES += "virtual/egl virtual/libgles1 virtual/libgles2"

RREPLACES_${PN} = "libegl libgles1 libgles2"
RREPLACES_${PN}-dev = "libegl-dev libgles1-dev libgles2-dev"
RREPLACES_${PN}-dbg = "libegl-dbg"

S = "${WORKDIR}/git"

do_install () {
    oe_runmake install DESTDIR=${D}
}

FILES_${PN} =  "${bindir}/*"
FILES_${PN} += " ${libdir}/*"
FILES_${PN} +=  "${includedir}/* /usr/share/sgx-lib/"

INHIBIT_PACKAGE_STRIP = "1"

INSANE_SKIP_${PN} += "dev-so ldflags useless-rpaths"
