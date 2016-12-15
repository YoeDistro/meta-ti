DESCRIPTION = "TI PRUSS low level driver library"

DEPENDS="common-csl-ip osal"

include pruss-lld.inc

PR = "${INC_PR}.1"

S = "${WORKDIR}/${PRUSS_LLD_GIT_DESTSUFFIX}"

do_compile () {
#   Build the lib
    make -f makefile_armv7 clean PDK_INSTALL_PATH=${STAGING_INCDIR}
    make -f makefile_armv7 lib PDK_INSTALL_PATH=${STAGING_INCDIR}
}

do_install () {
#   Install the lib
    make -f makefile_armv7 install PDK_INSTALL_PATH=${STAGING_INCDIR} INSTALL_INC_BASE_DIR=${D}${includedir} INSTALL_LIB_BASE_DIR=${D}${libdir}
}
