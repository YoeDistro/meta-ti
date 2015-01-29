DESCRIPTION = "TI softhsmv2 package"
LICENSE = "BSD-2-Clause | BSD-3-Clause | ISC | IBM-License | FSFULLR | Zlib"
LIC_FILES_CHKSUM = "file://LICENSE;md5=55144a048ba54f211a1aaa38c7fd2424"
SECTION = "libs"

mntdir = "/mnt"

inherit autotools pkgconfig

DEPENDS = "openssl libdaemon"
COMPATIBLE_MACHINE = "(tci6614-evm|keystone)"

BRANCH="master"
SRC_URI = "git://git.ti.com/keystone-linux/ti-softhsmv2.git;protocol=git;branch=${BRANCH}"
#Following commit corresponds to tag DEV.SOFTHSM-01.03.00.01
SRCREV = "c79d93ac2a326567a7245dda1b903ef340b5650c"
PV = "1.3.0.1"

S = "${WORKDIR}/git"

CFLAGS += " -mno-unaligned-access"
CPPFLAGS += " -mno-unaligned-access"

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
