DESCRIPTION = "Userspace libraries for PowerVR Rogue GPU on TI SoCs"
HOMEPAGE = "http://git.ti.com/graphics/ti-img-rogue-umlibs"
LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7232b98c1c58f99e3baa03de5207e76f"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "j7-evm"

PR = "r4"

BRANCH = "linuxws/thud/k4.19/${PV}"

SRC_URI = "git://git.ti.com/graphics/ti-img-rogue-umlibs.git;branch=${BRANCH}"
SRCREV = "ba99e1156c794f8618a743a4bb0f075b2ade9db3"

PVR_SOC ?= "j721e_linux"
PVR_BUILD ?= "release"
PVR_WS = "wayland"

INITSCRIPT_NAME = "rc.pvr"
INITSCRIPT_PARAMS = "defaults 8"

inherit update-rc.d

PROVIDES += "virtual/egl virtual/libgles1 virtual/libgles2"

DEPENDS += "libdrm"
RDEPENDS_${PN} += "bash"
RDEPENDS_${PN} += "python-core"
RDEPENDS_${PN} += "ti-img-rogue-driver"

RPROVIDES_${PN} = "libegl libgles1 libgles2"
RPROVIDES_${PN}-dev = "libegl-dev libgles1-dev libgles2-dev"
RPROVIDES_${PN}-dbg = "libegl-dbg libgles1-dbg libgles2-dbg"

RREPLACES_${PN} = "libegl libgles1 liblges2"
RREPLACES_${PN}-dev = "libegl-dev libgles1-dev libgles2-dev"
RREPLACES_${PN}-dbg = "libegl-dbg libgles1-dbg libgles2-dbg"

RCONFLICTS_${PN} = "libegl libgles1 libgles2"
RCONFLICTS_${PN}-dev = "libegl-dev libgles1-dev libgles2-dev"
RCONFLICTS_${PN}-dbg = "libegl-dbg libgles1-dbg libgles2-dbg"

S = "${WORKDIR}/git"

do_install () {
    oe_runmake install DESTDIR=${D} TARGET_PRODUCT=${PVR_SOC} BUILD=${PVR_BUILD} WINDOW_SYSTEM=${PVR_WS}
    chown -R root:root ${D}
}

FILES_${PN} += " ${base_libdir}/firmware/"
FILES_${PN} += " ${datadir}/"

INSANE_SKIP_${PN} += "ldflags arch already-stripped"

CLEANBROKEN = "1"
