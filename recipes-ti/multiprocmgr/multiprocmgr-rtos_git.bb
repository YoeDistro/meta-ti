SUMMARY = "TI Multiproc Manager (RTOS) for KeyStone II"
DESCRIPTION = "Provides download, debug and other utilities for other cores in the SOC like DSP"

require recipes-ti/includes/ti-paths.inc
require recipes-ti/multiprocmgr/multiprocmgr.inc

DEPENDS = "ti-cgt6x-native"

PR = "${INC_PR}.2"

PACKAGES =+ "${PN}-test"
FILES_${PN}-test = "${datadir}/ti/examples/mpm/test/filetestdemo/c66x/demo_loopback/build/bin/*.out \
                    ${datadir}/ti/examples/mpm/test/sync_test/c66x/bin/*.out"

EXTRA_OEMAKE = "LOCAL_SYSROOT="${STAGING_DIR_TARGET}" \
                C6X_GEN_INSTALL_PATH="${STAGING_DIR_NATIVE}/usr/share/ti/cgt-c6x" \
               "

do_compile() {
    oe_runmake c66x
    oe_runmake test_c66x
}

do_install() {
    CP_ARGS="-Prf --preserve=mode,timestamps --no-preserve=ownership"
    install -d ${D}${MPM_INSTALL_DIR_RECIPE}
    cp ${CP_ARGS} ${S}/* ${D}${MPM_INSTALL_DIR_RECIPE}

    # Copy C66x binaries
    install -d ${D}${datadir}/ti/examples/mpm/test/filetestdemo/c66x/demo_loopback/build/bin
    cp ${S}/test/filetestdemo/c66x/demo_loopback/build/bin/*.out \
        ${D}${datadir}/ti/examples/mpm/test/filetestdemo/c66x/demo_loopback/build/bin/
    install -d ${D}${datadir}/ti/examples/mpm/test/sync_test/c66x/bin
    cp ${S}/test/sync_test/c66x/bin/*.out \
        ${D}${datadir}/ti/examples/mpm/test/sync_test/c66x/bin/
}

FILES_${PN}-dev += "${MPM_INSTALL_DIR_RECIPE}"
INSANE_SKIP_${PN}-dev = "arch"
INSANE_SKIP_${PN}-test = "arch"

RDEPENDS_${PN}-dev = "bash"

ALLOW_EMPTY_${PN} = "1"
