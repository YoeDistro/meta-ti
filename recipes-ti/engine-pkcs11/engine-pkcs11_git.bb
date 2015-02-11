DESCRIPTION = "OpenSSL PKCS11 engine"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://src/engine_pkcs11.h;startline=1;endline=26;md5=471dacb7f0586e35729f422b8098585f"
SECTION = "libs"

inherit autotools pkgconfig

DEPENDS = "openssl libtool libp11"

PV = "1.3.0.0"
BRANCH="master"
# Commit corresponds to DEV.ENGINE_PKCS11-01.03.00.00
SRCREV = "0b14500b0ea8b37453b6d93aac02278465e7e732"

SRC_URI = "git://git.ti.com/keystone-linux/engine-pkcs11.git;protocol=git;branch=${BRANCH}"
S = "${WORKDIR}/git"

FILES_${PN} += "${libdir}/engines/*.so*"
FILES_${PN}-dbg += "${libdir}/engines/.debug"
