DESCRIPTION = "TI Digital Radio Front End lld test application"

DEPENDS = "common-csl-ip dfe-lld iqn2-lld"

include dfe-lld.inc

PR = "${INC_PR}.0"

EXTRA_OEMAKE = "-f makefile_armv7 PDK_INSTALL_PATH=${STAGING_INCDIR} DFE_SRC_DIR=${S}"

do_compile () {
#   Now build the lld in the updated directory
	oe_runmake clean DEVICE=k2l
	oe_runmake tests DEVICE=k2l
	oe_runmake tests DEVICE=k2l USEDYNAMIC_LIB=yes
}

do_install () {
	oe_runmake installbin INSTALL_BIN_BASE_DIR=${D}/${bindir}
}
