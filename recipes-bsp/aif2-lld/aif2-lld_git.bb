DESCRIPTION = "Antenna Interface low level driver"

DEPENDS = "common-csl-ip qmss-lld cppi-lld"

include aif2-lld.inc

PR = "${INC_PR}.0"

EXTRA_OEMAKE = "-f makefile_armv7 PDK_INSTALL_PATH=${STAGING_INCDIR}"

do_compile () {
    oe_runmake lib DEVICE=k2hk
}

do_install () {
    oe_runmake install INSTALL_INC_BASE_DIR=${D}/${includedir} INSTALL_LIB_BASE_DIR=${D}${libdir}
}
