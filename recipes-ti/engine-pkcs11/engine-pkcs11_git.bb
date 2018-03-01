DESCRIPTION = "OpenSSL PKCS11 engine"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://src/engine_pkcs11.h;startline=1;endline=26;md5=471dacb7f0586e35729f422b8098585f"
SECTION = "libs"

inherit autotools pkgconfig

DEPENDS = "openssl libtool libp11"

COMPATIBLE_MACHINE = "keystone"

BRANCH="master"

PV = "1.3.0.1"
# Commit corresponds to DEV.ENGINE_PKCS11-01.03.00.01
SRCREV = "56ac060e2245907a442376d586924af26ed7e478"

SRC_URI = "git://git.ti.com/keystone-linux/engine-pkcs11.git;protocol=git;branch=${BRANCH}"
S = "${WORKDIR}/git"

FILES_${PN} += "${libdir}/engines/*.so*"
FILES_${PN}-dbg += "${libdir}/engines/.debug"
