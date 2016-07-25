DESCRIPTION = "TI DSP Code Generation Tools"
HOMEPAGE = "http://processors.wiki.ti.com/index.php/Download_CCS#Code_Composer_Studio_Version_6_Downloads"
LICENSE = "(TI-TSPA & Thai-Open-Source-Software-Center) & BSD-3-Clause & BSL-1.0 & Hewlett-Packard & AFL-3.0 & MIT & BSD-2-Clause & PD"

LIC_FILES_CHKSUM = "file://${WORKDIR}/ccsv6/doc/EULA/EULA.doc;md5=24fb8a52ac52d3938b5888362edb295a"

inherit native

require recipes-ti/includes/ti-unpack.inc
require recipes-ti/includes/ti-staging.inc
require recipes-ti/includes/ti-paths.inc

TI_CGT_ARM_VERSION = "15.12.1.LTS"

SRC_URI = "http://install.source.dir.local/CCS${PV}_linux.tar.gz;name=ccsv6"

S = "${WORKDIR}/CCS${PV}_linux"

BINFILE = "CCS${PV}_linux/ccs_setup_${PV}.bin"
TI_BIN_UNPK_ARGS = "--mode unattended --prefix ${WORKDIR}"
TI_BIN_UNPK_CMDS = ""

SRC_URI[ccsv6.md5sum] = "ceaa5023c1a4b51bc637d3de1a15786b"
SRC_URI[ccsv6.sha256sum] = "fb3205a21942da09684c871371513707c841c4fd8d88a62f6a7865b0a86cc492"

do_install() {
    install -d ${D}${CCSV6_INSTALL_DIR_RECIPE}
    cp -r ${WORKDIR}/ccsv6/. ${D}${CCSV6_INSTALL_DIR_RECIPE}

    install -d ${D}${M4_TOOLCHAIN_INSTALL_DIR_RECIPE}
    cp -r ${WORKDIR}/ccsv6/tools/compiler/ti-cgt-arm_${TI_CGT_ARM_VERSION}/. \
          ${D}${M4_TOOLCHAIN_INSTALL_DIR_RECIPE}
}

FILES_${PN} += "\
  ${CCSV6_INSTALL_DIR_RECIPE} \
  ${M4_TOOLCHAIN_INSTALL_DIR_RECIPE} \
"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INSANE_SKIP_${PN} = "arch libdir ldflags staticdev textrel dev-so"

EXCLUDE_FROM_SHLIBS = "1"
