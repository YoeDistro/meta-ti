DESCRIPTION = "TI PRUSS low level driver tests"

DEPENDS="common-csl-ip pruss-lld osal"

include pruss-lld.inc

PR = "${INC_PR}.0"

S = "${WORKDIR}/${PRUSS_LLD_GIT_DESTSUFFIX}"

do_compile () {
#   Build the tests
    make -f makefile_armv7 tests PDK_INSTALL_PATH=${STAGING_INCDIR} PRUSS_SRC_DIR=${S}
}

do_install () {
#   Install the binary
    make -f makefile_armv7 installbin PDK_INSTALL_PATH=${STAGING_INCDIR} INSTALL_BIN_BASE_DIR=${D}${bindir} PRUSS_SRC_DIR=${S}
}
