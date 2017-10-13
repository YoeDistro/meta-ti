DESCRIPTION = "TI dsptop utility."
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://debian/copyright;md5=309825aa8f5edfcf2c44912ac094b979"

DEPENDS = "libulm ncurses"

PR = "${INC_PR}.2"

S = "${WORKDIR}/git/dsptop"

DEVICE = ""
DEVICE_dra7xx = "DRA7xx"
DEVICE_keystone = "C66AK2Hxx"

EXTRA_OEMAKE = "release DEVICE=${DEVICE} CROSS_COMPILE=${TARGET_PREFIX} CC="${CC}""

do_install() {
    oe_runmake install DESTDIR=${D}
}

COMPATIBLE_MACHINE = "dra7xx|keystone"
PACKAGE_ARCH = "${MACHINE_ARCH}"

RDEPENDS_${PN} = "debugss-module-drv bash"
RDEPENDS_${PN}_append_keystone = " temperature-module-drv"

include dsptop.inc

PARALLEL_MAKE = ""
