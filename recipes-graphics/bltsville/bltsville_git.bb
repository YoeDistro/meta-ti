DESCRIPTION = "Userspace libraries and headers for TI BLTsville implementation"
HOMEPAGE = "http://omapzoom.org/?p=platform/external/bltsville.git;a=summary"
LICENSE = "BSD-3-Clause & TI-TSPA"
LIC_FILES_CHKSUM = "file://COPYING;md5=10a9abb9c5bb19edd83a8cf66eef7148 \
                    file://ticpu/license;md5=0bb831850a0de80e32a63772d89c6562"

PR = "r3"

SRCREV = "a759bde8a5a6e518dfb8a6e633736a360005498e"

inherit autotools-brokensep pkgconfig

SRC_URI = "git://git.omapzoom.org/platform/external/bltsville.git;protocol=git"

S = "${WORKDIR}/git"

do_configure() {
	chmod +x autogen.sh
	./autogen.sh --prefix=${prefix} --with-libtool-sysroot=${STAGING_DIR_TARGET}
}

FILES_${PN} += "${libdir}/libbltsville_ticpu_license.txt"   
