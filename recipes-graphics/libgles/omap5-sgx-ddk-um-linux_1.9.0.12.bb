DESCRIPTION = "Userspace libraries for SGX"
HOMEPAGE = "http://downloads.ti.com/dsps/dsps_public_sw/gfxsdk"
LICENSE = "TI-TSPA"
LIC_FILES_CHKSUM = "file://OMAP5-Linux-Graphics-DDK-UM-Manifest.doc;md5=360d293df455e4f2d363bb4014a49603"

PACKAGE_ARCH = "${MACHINE_ARCH}"

BRANCH_omap-a15 = "master"
BRANCH_ti33x = "am4/k4.1"
BRANCH_ti43x = "am4/k4.1"

SRC_URI = "git://git.ti.com/graphics/omap5-sgx-ddk-um-linux.git;protocol=git;branch=${BRANCH}"
SRCREV_omap-a15 = "d63cd6469fff610317a8e1c986f227bb3e7922f6"
SRCREV_ti33x    = "c30d4b35701e1b88d58a9ae713b2e679298d1a37"
SRCREV_ti43x    = "c30d4b35701e1b88d58a9ae713b2e679298d1a37"

INITSCRIPT_NAME = "pvr-init"
INITSCRIPT_PARAMS = "defaults 8"

inherit update-rc.d

PR = "r10"
PROVIDES += "virtual/egl virtual/libgles1 virtual/libgles2"

RREPLACES_${PN} = "libegl libgles1 libgles2"
RREPLACES_${PN}-dev = "libegl-dev libgles1-dev libgles2-dev"
RREPLACES_${PN}-dbg = "libegl-dbg"

S = "${WORKDIR}/git"

SRC_URI_append = " \
    file://rc.pvr \
    file://powervr.ini \
"

do_install () {
    oe_runmake install DESTDIR=${D}
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/rc.pvr ${D}${sysconfdir}/init.d/pvr-init

    install -d ${D}${sysconfdir}
    install -m 0644 ${WORKDIR}/powervr.ini ${D}${sysconfdir}/
}

FILES_${PN} =  "${bindir}/*"
FILES_${PN} += " ${libdir}/*"
FILES_${PN} +=  "${includedir}/* /usr/share/sgx-lib/"
FILES_${PN} += "${sysconfdir}/init.d/pvr-init"
FILES_${PN} += "${sysconfdir}/powervr.ini"

INHIBIT_PACKAGE_STRIP = "1"

INSANE_SKIP_${PN} += "dev-so ldflags useless-rpaths"

CLEANBROKEN = "1"
