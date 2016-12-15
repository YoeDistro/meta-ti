DESCRIPTION = "TI OSAL tests"

DEPENDS="common-csl-ip osal"

include osal.inc

PR = "${INC_PR}.0"

S = "${WORKDIR}/${OSAL_GIT_DESTSUFFIX}"

do_compile () {
#   Build the tests
    make -f makefile_armv7 clean PDK_INSTALL_PATH=${STAGING_INCDIR}
    make -f makefile_armv7 tests PDK_INSTALL_PATH=${STAGING_INCDIR} OSAL_SRC_DIR=${S}
}

do_install () {
#   Install the binary
    make -f makefile_armv7 installbin PDK_INSTALL_PATH=${STAGING_INCDIR} INSTALL_BIN_BASE_DIR=${D}${bindir} OSAL_SRC_DIR=${S}
}
