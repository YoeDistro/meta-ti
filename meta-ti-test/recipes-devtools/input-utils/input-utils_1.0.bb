SUMMARY = "Utilities for the Linux input drivers"
HOMEPAGE = "http://packages.tanglu.org/source/aequorea/input-utils"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=8ca43cbc842c2336e835926c2166c28b"

PV = "1.0"
PR = "r0"

SRC_URI = "http://ftp.de.debian.org/debian/pool/main/i/input-utils/input-utils_1.0.orig.tar.gz \
	file://fix-ftbfs-echo.patch"
SRC_URI:append:arm = " file://0001-autoconf-fix-for-cross-compilation-for-ARM.patch"

SRC_URI[md5sum] = "3e58772e8647093b1de2f2c90bfb9ee8"
SRC_URI[sha256sum] = "ab6f550f01bb5fcede492ab88711d82bacee4229cf2f5dc55a349b3bff4e3b13"

S = "${UNPACKDIR}/input-1.0"

do_compile () {
    oe_runmake
}

# set the DESTDIR and the STRIP variables used by the GNUmakefile.
# The STRIP variable is set to blank or else the variable setting from OE
# is picked up as <TC>-strip and the install step sees that as another
# file to install.
EXTRA_OEMAKE = "DESTDIR=${D} STRIP=''"

do_install () {
    oe_runmake install
}
