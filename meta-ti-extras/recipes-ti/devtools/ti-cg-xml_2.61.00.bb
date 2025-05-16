SUMMARY = "TI CG_XML"
HOMEPAGE = "http://software-dl.ti.com/ccs/non-esd/releases/other/applications_packages/cg_xml/index.htm"
SECTION = "devel"
LICENSE = "BSD-3-Clause"

require recipes-ti/includes/ti-paths.inc
require recipes-ti/includes/ti-staging.inc
require recipes-ti/includes/ti-unpack.inc

PV = "2.61.00"
PE = "1"
S = "${WORKDIR}/cg_xml_${PV}"

SRC_URI = "\
    http://software-dl.ti.com/ccs/non-esd/releases/other/applications_packages/cg_xml/cg_xml_v${@'${PV}'.replace('.','_')}/cgxml-${PV}-linux-installer.run;name=cgxmlbin_linux \
"
LIC_FILES_CHKSUM = "file://cg_xml_${PV}_Manifest.pdf;md5=fc02a39748ba50373f32b2f4a5e3a684"

SRC_URI[cgxmlbin_linux.md5sum] = "6ce60caa97bbf15158d806fb062fadff"
SRC_URI[cgxmlbin_linux.sha256sum] = "da77cb10bd3d5de89e27e4ce8f4408a2e50775c8980225f9a828ddc242bb81f9"

BINFILE = "cgxml-${PV}-linux-installer.run"
TI_BIN_UNPK_CMDS = ""
TI_BIN_UNPK_ARGS = "--prefix ${S} --mode unattended"

do_install() {
    CP_ARGS="-Prf --preserve=mode,timestamps --no-preserve=ownership"
    install -d ${D}${CG_XML_INSTALL_DIR_RECIPE}
    cp ${CP_ARGS} ${S}/* ${D}${CG_XML_INSTALL_DIR_RECIPE}
}

FILES:${PN} += "${CG_XML_INSTALL_DIR_RECIPE}"

INSANE_SKIP:${PN} = "arch ldflags file-rdeps"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

BBCLASSEXTEND = "native nativesdk"
