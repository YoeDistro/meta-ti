DESCRIPTION = "TI OSAL tests"

DEPENDS="common-csl-ip osal"

include osal.inc

PR = "${INC_PR}.1"

S = "${WORKDIR}/${OSAL_GIT_DESTSUFFIX}"

EXTRA_OEMAKE = "-f makefile_armv7 PDK_INSTALL_PATH=${STAGING_INCDIR} OSAL_SRC_DIR=${S}"

do_compile () {
#   Build the tests
    oe_runmake clean
    oe_runmake tests
}

do_install () {
#   Install the binary
    oe_runmake installbin INSTALL_BIN_BASE_DIR=${D}${bindir}
}
