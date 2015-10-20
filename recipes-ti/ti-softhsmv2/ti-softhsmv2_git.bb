DESCRIPTION = "TI softhsmv2 package"
LICENSE = "BSD-2-Clause | BSD-3-Clause | ISC | IBM-License | FSFULLR | Zlib"
LIC_FILES_CHKSUM = "file://LICENSE;md5=664f3daf042b19d634189e709d5d558e"
SECTION = "libs"

mntdir = "/mnt"

inherit autotools pkgconfig

DEPENDS = "openssl libdaemon zlib"
COMPATIBLE_MACHINE = "(tci6614-evm|keystone)"

BRANCH="master"
SRC_URI = "git://git.ti.com/keystone-linux/ti-softhsmv2.git;protocol=git;branch=${BRANCH}"
#Following commit corresponds to tag DEV.SOFTHSM-01.03.00.04
SRCREV = "c2688afb9373309751e640088705cb739997665d"
PV = "1.3.0.4"

S = "${WORKDIR}/git"

CFLAGS += " -mno-unaligned-access"
CPPFLAGS += " -mno-unaligned-access"

EXTRA_OECONF += "--with-zlib=${STAGING_DIR_HOST}/usr"

INITSCRIPT_NAME = "softhsm-daemon.sh"
INITSCRIPT_PARAMS = "defaults 10"

inherit update-rc.d

SELECTED_OPTIMIZATION := "${@d.getVar("SELECTED_OPTIMIZATION", True).replace("-O2", "-O3")}"
SELECTED_OPTIMIZATION := "${@d.getVar("SELECTED_OPTIMIZATION", True).replace("-ggdb2", "")}"

FILES_${PN} += "${libdir}/softhsm/lib*.so.* ${mntdir}/*"
FILES_${PN}-dbg += "${libdir}/softhsm/.debug"
FILES_${PN}-staticdev += "${libdir}/softhsm/*.a "
FILES_${PN}-dev += "${libdir}/softhsm/*.la ${libdir}/softhsm/lib*.so"

do_install_append() {
	install -d ${D}${mntdir}/securedbv0
	install -d ${D}${mntdir}/securedbv1

	install -d ${D}${sysconfdir}/init.d/
	install -c -m 755 ${S}/src/bin/daemon/softhsm-daemon.sh ${D}${sysconfdir}/init.d/${INITSCRIPT_NAME}
}
