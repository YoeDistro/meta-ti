SUMMARY = "TI Multiproc Manager test code"

include multiprocmgr.inc

PR = "${INC_PR}.2"

DEPENDS = "multiprocmgr cmem"
RDEPENDS:${PN} = "multiprocmgr mpm-transport cmem"
RDEPENDS:${PN} += "multiprocmgr-rtos-test bash"

FILES:${PN} += "\
    ${datadir}/ti/examples/mpm \
"

FILES:${PN}-dbg += "\
    ${datadir}/ti/examples/mpm/*/.debug \
"

do_compile() {
	oe_runmake -C ${S} test
}

do_install() {
	# Copy Sources and binary
	install -d ${D}${datadir}/ti/examples/mpm/src
	cp -r ${S}/src/mailbox ${D}${datadir}/ti/examples/mpm/src
	cp -r ${S}/src/sync ${D}${datadir}/ti/examples/mpm/src
	cp -r ${S}/test ${D}${datadir}/ti/examples/mpm
}

INSANE_SKIP:${PN} = "ldflags staticdev"
