DESCRIPTION = "TI softhsmv2 package"
LICENSE = "BSD-2-Clause | BSD-3-Clause | ISC | IBM-License | FSFULLR | Zlib"
LIC_FILES_CHKSUM = "file://LICENSE;md5=700a883962ccff663f888f3f7221ce8f"
SECTION = "libs"

mntdir = "/mnt"

inherit autotools pkgconfig

DEPENDS = "openssl libdaemon zlib"
COMPATIBLE_MACHINE = "keystone"

BRANCH="ti-softhsm-rebase"
SRC_URI = "git://git.ti.com/keystone-linux/ti-softhsmv2.git;protocol=git;branch=${BRANCH}"
#Following commit corresponds to tag DEV.SOFTHSM-02.00.00.00
SRCREV = "b0eef602c03583e59c289ba113b64eaa4f5cac13"
PV = "2.0.0.0"
PR = "r0"

S = "${WORKDIR}/git"

CFLAGS += " -mno-unaligned-access"
CPPFLAGS += " -mno-unaligned-access"

EXTRA_OECONF += " \
    --with-zlib=${STAGING_EXECPREFIXDIR} \
    --with-openssl=${STAGING_EXECPREFIXDIR} \
    "

INITSCRIPT_NAME = "softhsm-daemon.sh"
INITSCRIPT_PARAMS = "defaults 10"

inherit update-rc.d

FILES_${PN} += "${libdir}/softhsm/lib*.so.* ${mntdir}/*"
FILES_${PN}-dbg += "${libdir}/softhsm/.debug"
FILES_${PN}-staticdev += "${libdir}/softhsm/*.a "
FILES_${PN}-dev += "${libdir}/softhsm/*.la ${libdir}/softhsm/lib*.so"

INSANE_SKIP_${PN}-dev = "dev-elf"

do_install_append() {
	install -d ${D}${mntdir}/securedbv0
	install -d ${D}${mntdir}/securedbv1

	install -d ${D}${sysconfdir}/init.d/
	install -c -m 755 ${S}/src/bin/daemon/softhsm-daemon.sh ${D}${sysconfdir}/init.d/${INITSCRIPT_NAME}
}
