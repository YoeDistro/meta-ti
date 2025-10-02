SUMMARY = "TI dsptop utility."
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://debian/copyright;md5=309825aa8f5edfcf2c44912ac094b979"

DEPENDS = "libulm ncurses"

PR = "${INC_PR}.2"

S = "${WORKDIR}/git/dsptop"

COMPATIBLE_MACHINE = "omap-a15"

EXTRA_OEMAKE = "release DEVICE=DRA7xx CROSS_COMPILE=${TARGET_PREFIX} CC="${CC}""

do_install() {
    oe_runmake install DESTDIR=${D}
    chown -R root:root ${D}
}

RDEPENDS:${PN} = "debugss-module-drv bash"

include dsptop.inc

PARALLEL_MAKE = ""
