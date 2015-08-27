DESCRIPTION = "Userspace libraries for SGX"
HOMEPAGE = "http://downloads.ti.com/dsps/dsps_public_sw/gfxsdk"
LICENSE = "TI-TSPA"
LIC_FILES_CHKSUM = "file://OMAP5-Linux-Graphics-DDK-UM-Manifest.doc;md5=360d293df455e4f2d363bb4014a49603"

BRANCH = "dra7/k3.14"

SRC_URI = "git://git.ti.com/graphics/omap5-sgx-ddk-um-linux.git;protocol=git;branch=${BRANCH}"
SRCREV = "d63cd6469fff610317a8e1c986f227bb3e7922f6"

PR = "r10"
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
