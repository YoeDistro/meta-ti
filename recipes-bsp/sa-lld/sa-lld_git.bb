DESCRIPTION = "TI Security Accelerator LLD (SA LLD) Library"

DEPENDS = "common-csl-ip"

include sa-lld.inc

PR = "${INC_PR}.1"

PARALLEL_MAKE = ""
EXTRA_OEMAKE = "-f makefile_armv7 PDK_INSTALL_PATH=${STAGING_INCDIR}"

do_compile () {
#   Now build the lld in the updated directory
	oe_runmake clean lib
}

do_install () {
	oe_runmake install INSTALL_INC_BASE_DIR=${D}/${includedir} INSTALL_LIB_BASE_DIR=${D}${libdir}
	chown -R root:root ${D}

#   Set the generic device library symbolic link
	ln -sf libsa.so.1.0.0 ${D}${libdir}/libsa_device.so.1
	ln -sf libsa_device.so.1 ${D}${libdir}/libsa_device.so
}

INHIBIT_PACKAGE_STRIP_FILES = "${PKGD}${libdir}/libsa.a"
