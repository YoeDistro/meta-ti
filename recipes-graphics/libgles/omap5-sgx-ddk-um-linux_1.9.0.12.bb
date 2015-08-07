DESCRIPTION = "Userspace libraries for SGX"
HOMEPAGE = "http://downloads.ti.com/dsps/dsps_public_sw/gfxsdk"
LICENSE = "TI-TSPA"
LIC_FILES_CHKSUM = "file://OMAP5-Linux-Graphics-DDK-UM-Manifest.doc;md5=360d293df455e4f2d363bb4014a49603"

BRANCH = "omap5/next"

SRC_URI = "git://git.ti.com/graphics/omap5-sgx-ddk-um-linux.git;protocol=git;branch=${BRANCH}"
SRCREV = "4ac0dba02717a7d7196f0b38b1fd6366f5b04ca7"

PR = "r8"
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

CLEANBROKEN = "1"
