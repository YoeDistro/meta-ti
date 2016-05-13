DESCRIPTION = "Test code for user space IO (UIO) driver"

include uio-module-drv.inc

PR = "r1"

COMPATIBLE_MACHINE = "keystone"
RDEPENDS_${PN} = "uio-module-drv"

do_compile() {
	oe_runmake -C ${S} test
}

do_install() {
	install -d ${D}${bindir}/
	install -c -m 755 ${S}/test/uio_cic2_int_multithread_test ${D}${bindir}/.
}
