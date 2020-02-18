SUMMARY = "TI Multiproc Manager for KeyStone II"
DESCRIPTION = "Provides download, debug and other utilities for other cores in the SOC like DSP"

include multiprocmgr.inc

SRC_URI += " \
	file://mpmsrv-daemon.service \
"

PR = "${INC_PR}.2"

DEPENDS = "mpm-transport libdaemon virtual/kernel cmem"

PACKAGE_ARCH = "${MACHINE_ARCH}"

INITSCRIPT_NAME = "mpmsrv-daemon.sh"
INITSCRIPT_PARAMS = "defaults 10"

SYSTEMD_SERVICE_${PN} = "mpmsrv-daemon.service"

inherit update-rc.d systemd

do_install() {
	install -d ${D}${bindir}/
	install -c -m 755 ${S}/bin/mpmsrv ${D}${bindir}/mpmsrv
	install -c -m 755 ${S}/bin/mpmcl ${D}${bindir}/mpmcl

	install -d ${D}${systemd_system_unitdir}
	install -m 0644 ${WORKDIR}/mpmsrv-daemon.service ${D}${systemd_system_unitdir}
	install -d ${D}${sysconfdir}/init.d/
	install -c -m 755 ${S}/scripts/mpmsrv-daemon.sh ${D}${sysconfdir}/init.d/${INITSCRIPT_NAME}

	install -d ${D}${sysconfdir}/mpm/
	install -c -m 755 ${S}/scripts/crash_callback.sh ${D}${sysconfdir}/mpm/crash_callback.sh

	install -d ${D}${includedir}/
	install -c -m 755 ${S}/include/* ${D}${includedir}/

	install -d ${D}${libdir}/
	cp -a ${S}/lib/* ${D}${libdir}/
	chown -R root:root ${D}${libdir}/
}

INSANE_SKIP_${PN} = "ldflags"
