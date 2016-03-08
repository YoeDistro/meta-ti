DESCRIPTION = "TI Multiproc Manager test code"

include multiprocmgr.inc

PR = "${INC_PR}.1"

DEPENDS = "multiprocmgr cmem"
RDEPENDS_${PN} = "multiprocmgr mpm-transport cmem"

CC += "-I${STAGING_KERNEL_DIR}/include"

# Assuming the multiprocmgr kernel API is safe
CC[vardepsexclude] = "STAGING_KERNEL_DIR"

do_compile() {
	make -C ${S} test
}

do_install() {
	install -d ${D}${bindir}/
	install -c -m 755 ${S}/test/filetestdemo/host/bin/demo_filetest ${D}${bindir}/mpm_demo_filetest
	install -c -m 755 ${S}/test/sync_test/host/bin/sync_test ${D}${bindir}/mpm_sync_test
}
