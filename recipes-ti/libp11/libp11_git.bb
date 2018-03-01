DESCRIPTION = "PKCS11 abstraction library"
LICENSE = "LGPL2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=fad9b3332be894bab9bc501572864b29"
SECTION = "libs"

inherit autotools pkgconfig

DEPENDS = "openssl libtool"
RDEPENDS_${PN} = "ti-softhsmv2"

COMPATIBLE_MACHINE = "keystone"

SRC_URI = "git://git.ti.com/keystone-linux/libp11.git;protocol=git;branch=${BRANCH}"
BRANCH="master"
# Following commit corresponds to DEV.LIBP11-01.03.00.00
SRCREV = "43c4f63a8d803fde2bcd8e4f8969e5fe9edf0e33"
PV = "1.3.0.0"

S = "${WORKDIR}/git"
