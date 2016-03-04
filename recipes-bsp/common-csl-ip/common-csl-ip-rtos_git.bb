require common-csl-ip.inc
PR = "${INC_PR}.2"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit ti-pdk

DEPENDS_remove = "${PN}"

XDCARGS_ti33x = "am335x"
XDCARGS_ti43x = "am437x"
XDCARGS_omap-a15 = "am571x am572x"
XDCARGS_k2hk-evm = "k2h k2k"
XDCARGS_k2l-evm = "k2l"
XDCARGS_k2e-evm = "k2e"
XDCARGS_k2g-evm = "k2g"

do_configure_append() {
    # Create empty makefile
    # If libraries are supported for this device, then this will be overwritten
    cat > ${BUILD_DIR}/makefile << __EOF__
# Nothing to do
all:

__EOF__
}
