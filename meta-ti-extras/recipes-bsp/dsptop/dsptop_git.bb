DESCRIPTION = "TI dsptop utility."
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://debian/copyright;md5=309825aa8f5edfcf2c44912ac094b979"

DEPENDS = "libulm ncurses"

PR = "${INC_PR}.2"

S = "${WORKDIR}/git/dsptop"

DEVICE = ""
DEVICE:dra7xx = "DRA7xx"

EXTRA_OEMAKE = "release DEVICE=${DEVICE} CROSS_COMPILE=${TARGET_PREFIX} CC="${CC}""

do_install() {
    oe_runmake install DESTDIR=${D}
    chown -R root:root ${D}
}

COMPATIBLE_MACHINE = "dra7xx"
PACKAGE_ARCH = "${MACHINE_ARCH}"

RDEPENDS:${PN} = "debugss-module-drv bash"

include dsptop.inc

PARALLEL_MAKE = ""
