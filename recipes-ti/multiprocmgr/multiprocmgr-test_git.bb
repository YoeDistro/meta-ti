FILESEXTRAPATHS_prepend := "${THISDIR}/multiprocmgr:"

SUMMARY = "TI Multiproc Manager test code"

include multiprocmgr.inc

SRC_URI += " \
	file://0001-mpmdlif.c-don-t-hardcode-kernel-s-uapi-location-keys.patch \
	file://0001-mpmdlif.c-don-t-suppress-kernel-linux-types.h-inclus.patch \
"

PR = "${INC_PR}.2"

DEPENDS = "multiprocmgr cmem"
RDEPENDS_${PN} = "multiprocmgr mpm-transport cmem"
RDEPENDS_${PN} += "multiprocmgr-rtos-test bash"

FILES_${PN} += "\
    ${datadir}/ti/examples/mpm \
"

FILES_${PN}-dbg += "\
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
