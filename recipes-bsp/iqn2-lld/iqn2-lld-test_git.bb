DESCRIPTION = "TI IQN2 peripheral module low level driver test binaries"

DEPENDS = "common-csl-ip iqn2-lld dfe-lld"

include iqn2-lld.inc

PR = "${INC_PR}.0"

EXTRA_OEMAKE = "-f makefile_armv7 PDK_INSTALL_PATH=${STAGING_INCDIR} IQN2_SRC_DIR=${WORKDIR}/git"

do_compile () {
	oe_runmake clean DEVICE=k2l
	oe_runmake tests DEVICE=k2l
}

do_install () {
	oe_runmake installbin DEVICE=k2l IQN2_SRC_DIR=${WORKDIR}/git INSTALL_BIN_BASE_DIR=${D}/${bindir}
}
