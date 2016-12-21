DESCRIPTION = "TI PRUSS low level driver tests"

DEPENDS="common-csl-ip pruss-lld osal"

include pruss-lld.inc

PR = "${INC_PR}.1"

S = "${WORKDIR}/${PRUSS_LLD_GIT_DESTSUFFIX}"

EXTRA_OEMAKE += "-f makefile_armv7 PDK_INSTALL_PATH=${STAGING_INCDIR} PRUSS_SRC_DIR=${S}"

do_compile () {
#   Build the tests
    oe_runmake tests
}

do_install () {
#   Install the binary
    oe_runmake installbin INSTALL_BIN_BASE_DIR=${D}${bindir}
}
