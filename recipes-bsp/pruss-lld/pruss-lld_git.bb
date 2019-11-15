DESCRIPTION = "TI PRUSS low level driver library"

DEPENDS = "common-csl-ip osal"

include pruss-lld.inc

PR = "${INC_PR}.2"

EXTRA_OEMAKE += "-f makefile_armv7 PDK_INSTALL_PATH=${STAGING_INCDIR}"

do_compile () {
#   Build the lib
    oe_runmake clean
    oe_runmake lib
}

do_install () {
#   Install the lib
    oe_runmake install INSTALL_INC_BASE_DIR=${D}${includedir} INSTALL_LIB_BASE_DIR=${D}${libdir}
    chown -R root:root ${D}
}
