DESCRIPTION = "TI Multiproc Manager test code"

include multiprocmgr.inc

PR = "${INC_PR}.2"

DEPENDS = "multiprocmgr cmem"
RDEPENDS_${PN} = "multiprocmgr mpm-transport cmem"
RDEPENDS_${PN} += "multiprocmgr-rtos-test bash"

CC += "-I${STAGING_KERNEL_DIR}/include"

# Assuming the multiprocmgr kernel API is safe
CC[vardepsexclude] = "STAGING_KERNEL_DIR"

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
