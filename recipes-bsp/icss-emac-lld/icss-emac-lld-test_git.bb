DESCRIPTION = "TI ICSS-EMAC low level driver tests"

DEPENDS="common-csl-ip pruss-lld icss-emac-lld osal ti-pdk-build-rtos icss-emac-lld-rtos"

DEPENDS_append_ti33x = " starterware"
DEPENDS_append_ti43x = " starterware"

include icss-emac-lld.inc

PR = "${INC_PR}.0"

EXTRA_OEMAKE += "-f makefile_armv7 PDK_INSTALL_PATH=${STAGING_INCDIR} PDK_PKG_PATH=${STAGING_DATADIR}/ti/ti-pdk-tree/packages ICSS_EMAC_SRC_DIR=${S}"

DEVICE_LIST ?= "${TI_PDK_LIMIT_SOCS}"

do_compile () {
    # Build the tests
    for device in ${DEVICE_LIST}
    do
        oe_runmake tests DEVICE="$device"
    done
}

do_install() {
    # Install the binary
    for device in ${DEVICE_LIST}
    do
        oe_runmake installbin INSTALL_BIN_BASE_DIR=${D}${bindir} DEVICE="$device"
    done
}
