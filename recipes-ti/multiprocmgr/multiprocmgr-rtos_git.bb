SUMMARY = "TI Multiproc Manager (RTOS) for KeyStone II"
DESCRIPTION = "Provides download, debug and other utilities for other cores in the SOC like DSP"

require recipes-ti/includes/ti-paths.inc
require recipes-ti/multiprocmgr/multiprocmgr.inc

DEPENDS = "ti-cgt6x-native"

PR = "${INC_PR}.0"

export LOCAL_SYSROOT="${STAGING_DIR_TARGET}"
export C6X_GEN_INSTALL_PATH="${STAGING_DIR_NATIVE}/usr/share/ti/cgt-c6x"

do_compile() {
    make c66x
    make test_c66x
    sourceipk_do_create_srcipk
}

do_install() {
    install -d ${D}${MPM_INSTALL_DIR_RECIPE}
    cp -pPrf ${S}/* ${D}${MPM_INSTALL_DIR_RECIPE}
}

FILES_${PN}-dev += "${MPM_INSTALL_DIR_RECIPE}"
INSANE_SKIP_${PN}-dev = "arch"

ALLOW_EMPTY_${PN} = "1"
