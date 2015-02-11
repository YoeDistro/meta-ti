DESCRIPTION = "TI IQN2 peripheral module low level driver"

DEPENDS = "common-csl-ip"

include iqn2-lld.inc

EXTRA_OEMAKE = "-f makefile_armv7 PDK_INSTALL_PATH=${STAGING_INCDIR}"

do_compile () {
	oe_runmake clean
	oe_runmake lib
}

do_install () {
	oe_runmake install INSTALL_INC_BASE_DIR=${D}/${includedir} INSTALL_LIB_BASE_DIR=${D}${libdir}
}
