DESCRIPTION = "TI PRUSS low level driver tests"

DEPENDS = "common-csl-ip pruss-lld osal"

include pruss-lld.inc

PR = "${INC_PR}.2"

EXTRA_OEMAKE += "-f makefile_armv7 PDK_INSTALL_PATH=${STAGING_INCDIR} PRUSS_SRC_DIR=${S}"

DEVICE_LIST ?= "${TI_PDK_LIMIT_SOCS}"

do_compile () {
#   Build the tests
    for device in ${DEVICE_LIST}
    do
        oe_runmake tests DEVICE="$device"
    done
}

do_install () {
#   Install the binary
    for device in ${DEVICE_LIST}
    do
        oe_runmake installbin INSTALL_BIN_BASE_DIR=${D}${bindir} DEVICE="$device"
    done
}
