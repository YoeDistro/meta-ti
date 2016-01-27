DESCRIPTION = "TI Multiproc Manager for KeyStone II"
SUMMARY = "Provides download, debug and other utilities for other cores in the SOC like DSP"

include multiprocmgr.inc

PR = "${INC_PR}.0"

DEPENDS = "mpm-transport libdaemon virtual/kernel"
RDEPENDS_${PN} = "syslog-ng"

PACKAGE_ARCH = "${MACHINE_ARCH}"

CC += "-I${STAGING_KERNEL_DIR}/include"

INITSCRIPT_NAME = "mpmsrv-daemon.sh"
INITSCRIPT_PARAMS = "defaults 10"

inherit update-rc.d

do_install() {
	install -d ${D}${bindir}/
	install -c -m 755 ${S}/bin/mpmsrv ${D}${bindir}/mpmsrv
	install -c -m 755 ${S}/bin/mpmcl ${D}${bindir}/mpmcl

	install -d ${D}${sysconfdir}/init.d/
	install -c -m 755 ${S}/scripts/mpmsrv-daemon.sh ${D}${sysconfdir}/init.d/${INITSCRIPT_NAME}
	install -d ${D}${sysconfdir}/mpm/
	install -c -m 755 ${S}/scripts/crash_callback.sh ${D}${sysconfdir}/mpm/crash_callback.sh

	install -d ${D}${includedir}/
	install -c -m 755 ${S}/include/* ${D}${includedir}/

	install -d ${D}${libdir}/
	cp -a ${S}/lib/* ${D}${libdir}/
}
