include ipc-transport-qmss.inc

PR = "${INC_PR}.0"

DEPENDS = "ti-ipc mpm-transport"

do_compile () {
#   Now build the transport
	oe_runmake clean
	oe_runmake lib
}

do_install () {
	oe_runmake install INSTALL_INC_BASE_DIR=${D}${includedir} \
		INSTALL_LIB_BASE_DIR=${D}${libdir}
	chown -R root:root ${D}
}

INHIBIT_PACKAGE_STRIP_FILES = "${PKGD}${libdir}/libTransportQmss.a"
