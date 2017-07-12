DESCRIPTION = "TI ICSS-EMAC low level driver tests"

DEPENDS="common-csl-ip pruss-lld icss-emac-lld osal"

DEPENDS_append_ti33x = " starterware"
DEPENDS_append_ti43x = " starterware"

include icss-emac-lld.inc

PR = "${INC_PR}.0"

COMPATIBLE_MACHINE = "am57xx-evm|ti33x|ti43x"

EXTRA_OEMAKE += "-f makefile_armv7 PDK_INSTALL_PATH=${STAGING_INCDIR} ICSS_EMAC_SRC_DIR=${S}"

do_compile_am57xx-evm () {
#   Build the tests
    oe_runmake tests DEVICE="am571x" TARGET="SOC_AM571x"
    oe_runmake clean
    oe_runmake tests DEVICE="am572x" TARGET="SOC_AM572x"
}

do_compile_ti33x () {
#   Build the tests
    oe_runmake tests DEVICE="am335x" TARGET="SOC_AM335x"
}

do_compile_ti43x () {
#   Build the tests
    oe_runmake tests DEVICE="am437x" TARGET="SOC_AM437x"
}

do_install_am57xx-evm () {
#   Install the binary
    oe_runmake installbin INSTALL_BIN_BASE_DIR=${D}${bindir} DEVICE="am571x" TARGET="SOC_AM571x"
    oe_runmake installbin INSTALL_BIN_BASE_DIR=${D}${bindir} DEVICE="am572x" TARGET="SOC_AM572x"
}

do_install_ti33x () {
#   Install the binary
    oe_runmake installbin INSTALL_BIN_BASE_DIR=${D}${bindir} DEVICE="am335x" TARGET="SOC_AM335x"
}

do_install_ti43x () {
#   Install the binary
    oe_runmake installbin INSTALL_BIN_BASE_DIR=${D}${bindir} DEVICE="am437x" TARGET="SOC_AM437x"
}
