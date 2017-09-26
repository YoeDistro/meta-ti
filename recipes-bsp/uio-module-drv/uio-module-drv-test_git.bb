DESCRIPTION = "Test code for user space IO (UIO) driver"

include uio-module-drv.inc

PR = "r1"

COMPATIBLE_MACHINE = "keystone|omap-a15|ti33x|ti43x"
PACKAGE_ARCH = "${MACHINE_ARCH}"

DEVICE_NAME_omap-a15 = "am57xx"
DEVICE_NAME_ti33x = "am33xx"
DEVICE_NAME_ti43x = "am43xx"
DEVICE_NAME_keystone = "keystone"
DEVICE_NAME_k2g = "k2g"
RDEPENDS_${PN} = "uio-module-drv"

do_compile() {
	oe_runmake -C ${S} test "DEVICE=${DEVICE_NAME}"
}

do_install() {
	install -d ${D}${bindir}/
	install -c -m 755 ${S}/test/uio_mem_test ${D}${bindir}/.
	install -c -m 755 ${S}/test/uio_int_test ${D}${bindir}/.
}

do_install_append_keystone () {
	install -c -m 755 ${S}/test/uio_cic2_int_multithread_test ${D}${bindir}/.
}

do_install_append_k2g () {
        install -c -m 755 ${S}/test/prussdrv_test/test/pruss_uio_test ${D}${bindir}/.
}

do_install_append_omap-a15 () {
	install -c -m 755 ${S}/test/prussdrv_test/test/pruss_uio_test ${D}${bindir}/.
}

do_install_append_ti33x () {
        install -c -m 755 ${S}/test/prussdrv_test/test/pruss_uio_test ${D}${bindir}/.
}

do_install_append_ti43x () {
        install -c -m 755 ${S}/test/prussdrv_test/test/pruss_uio_test ${D}${bindir}/.
}
