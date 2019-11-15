DESCRIPTION = "TI PRUSS low level driver example applications"

DEPENDS="common-csl-ip pruss-lld osal ti-pdk-build-rtos pruss-lld-rtos"

DEPENDS_append_ti33x = " starterware"
DEPENDS_append_ti43x = " starterware"

include pruss-lld.inc

PR = "${INC_PR}.0"

EXTRA_OEMAKE += "-f makefile_armv7 PDK_INSTALL_PATH=${STAGING_INCDIR} PDK_PKG_PATH=${STAGING_DATADIR}/ti/ti-pdk-tree/packages PRUSS_SRC_DIR=${S}"


DEVICE_LIST ?= "${TI_PDK_LIMIT_SOCS}"

do_compile () {
#   Build the apps
    for device in ${DEVICE_LIST}
    do
        oe_runmake apps DEVICE="$device"
    done
}

do_install () {
#   Install the app binary
    for device in ${DEVICE_LIST}
    do
        oe_runmake installapp INSTALL_BIN_BASE_DIR=${D}${bindir} DEVICE="$device"
    done
}
