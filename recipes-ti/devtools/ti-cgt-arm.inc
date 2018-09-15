SUMMARY = "TI ARM Code Generation Tools"
HOMEPAGE = "https://www-a.ti.com/downloads/sds_support/TICodegenerationTools/download.htm"
LICENSE = "(TI-TSPA & Thai-Open-Source-Software-Center) & BSD-3-Clause & BSL-1.0 & Hewlett-Packard & AFL-3.0 & MIT & BSD-2-Clause & PD & BSD-4-Clause"

inherit native
require recipes-ti/includes/ti-unpack.inc
require recipes-ti/includes/ti-staging.inc
require recipes-ti/includes/ti-paths.inc

S = "${WORKDIR}/ti-cgt-arm-${PV}"

PR = "r1"

SRC_URI = "http://software-dl.ti.com/codegen/esd/cgt_public_sw/TMS470/${PV}.LTS/${BINFILE};name=${BINFILE_NAME}"

BINFILE = "ti_cgt_tms470_${PV}.LTS_linux_installer_x86.bin"
TI_BIN_UNPK_ARGS = "--prefix ${S}"
TI_BIN_UNPK_CMDS = ""

do_compile_append() {
    cd ${S}/ti-cgt-arm_${PV}.LTS
    export PATH=${PATH}:${S}/ti-cgt-arm_${PV}.LTS/bin
    lib/mklib --pattern=rtsv7R4_A_le_v3D16_eabi.lib
}

do_install() {
    install -d ${D}${M4_TOOLCHAIN_INSTALL_DIR_RECIPE}
    cp -r ${S}/ti-cgt-arm*/. ${D}${M4_TOOLCHAIN_INSTALL_DIR_RECIPE}
}

SRC_URI[cgt_arm_installer.md5sum] = "${BINFILE_MD5}"
SRC_URI[cgt_arm_installer.sha256sum] = "${BINFILE_SHA256}"

BINFILE_NAME = "cgt_arm_installer"

FILES_${PN} += "${M4_TOOLCHAIN_INSTALL_DIR_RECIPE}"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

INSANE_SKIP_${PN} += "arch staticdev"
