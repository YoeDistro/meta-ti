DESCRIPTION = "TI KEYSTONE mmap driver using Keystone MPAX Programming"
include mmap-lld.inc

PR = "${INC_PR}.0"

DEPENDS="common-csl-ip"

do_compile () {
	make -f makefile_armv7 clean lib PDK_INSTALL_PATH=${STAGING_INCDIR}
}

do_install () {
	make -f makefile_armv7 install PDK_INSTALL_PATH=${STAGING_INCDIR} INSTALL_INC_BASE_DIR=${D}/${includedir} INSTALL_LIB_BASE_DIR=${D}${libdir}
	chown -R root:root ${D}
}
