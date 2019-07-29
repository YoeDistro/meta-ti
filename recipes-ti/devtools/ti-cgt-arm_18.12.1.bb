SUMMARY = "TI ARM Code Generation Tools"
HOMEPAGE = "https://www-a.ti.com/downloads/sds_support/TICodegenerationTools/download.htm"
LICENSE = "(TI-TSPA & Thai-Open-Source-Software-Center) & BSD-3-Clause & BSL-1.0 & Hewlett-Packard & Patrick-Powell & AFL-3.0 & MIT & BSD-2-Clause & PD & BSD-4-Clause"

LIC_FILES_CHKSUM = "file://ti-cgt-arm_${PV}.LTS/README.txt;md5=ab7a61241e90fcd144a756d88294cf30"

require recipes-ti/includes/ti-unpack.inc
require recipes-ti/includes/ti-staging.inc
require recipes-ti/includes/ti-paths.inc

S = "${WORKDIR}/ti-cgt-arm-${PV}"

PR = "r0"

SRC_URI = "http://software-dl.ti.com/codegen/esd/cgt_public_sw/TMS470/${PV}.LTS/${BINFILE};name=${BINFILE_NAME}"

BINFILE = "ti_cgt_tms470_${PV}.LTS_linux_installer_x86.bin"
TI_BIN_UNPK_ARGS = "--prefix ${S}"
TI_BIN_UNPK_CMDS = ""

# only x86_64 is supported
COMPATIBLE_HOST = "x86_64.*-linux"
COMPATIBLE_HOST_class-target = "null"

do_install() {
    install -d ${D}${M4_TOOLCHAIN_INSTALL_DIR_RECIPE}
    cp -r ${S}/ti-cgt-arm*/. ${D}${M4_TOOLCHAIN_INSTALL_DIR_RECIPE}
}

SRC_URI[cgt_arm_installer.md5sum] = "af2378cf8d5d6f200ef3329f5a065529"
SRC_URI[cgt_arm_installer.sha256sum] = "d90ae2360cd5b22912715ae7898d99352da1b97fd655f9a55d0d19a2a46bab0e"

BINFILE_NAME = "cgt_arm_installer"

FILES_${PN} += "${M4_TOOLCHAIN_INSTALL_DIR_RECIPE}"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

INSANE_SKIP_${PN} += "arch staticdev"

BBCLASSEXTEND = "native nativesdk"
