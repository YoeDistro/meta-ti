DESCRIPTION = "TI Antenna Interface peripheral module low level driver test binaries"

DEPENDS = "common-csl-ip aif2-lld qmss-lld cppi-lld"

include aif2-lld.inc

PR = "${INC_PR}.0"

EXTRA_OEMAKE = "-f makefile_armv7 PDK_INSTALL_PATH=${STAGING_INCDIR} AIF2_SRC_DIR=${WORKDIR}/git"

do_compile () {
    oe_runmake clean DEVICE=k2hk
    oe_runmake tests DEVICE=k2hk
}

do_install () {
    oe_runmake installbin DEVICE=k2hk AIF2_SRC_DIR=${WORKDIR}/git INSTALL_BIN_BASE_DIR=${D}/${bindir}
}
