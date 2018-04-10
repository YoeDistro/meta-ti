DESCRIPTION = "TI CG_XML"
HOMEPAGE = "http://software-dl.ti.com/ccs/non-esd/releases/other/applications_packages/cg_xml/index.htm"
SECTION = "devel"
LICENSE = "BSD"

require recipes-ti/includes/ti-paths.inc
require recipes-ti/includes/ti-staging.inc
require recipes-ti/includes/ti-unpack.inc

PV = "2_41_00"

S = "${WORKDIR}/cg_xml_${PV}"

SRC_URI = "\
    http://software-dl.ti.com/ccs/non-esd/releases/other/applications_packages/cg_xml/cg_xml_v${PV}/cg_xml-v${PV}-Linux-x86-Install;name=cgxmlbin_linux \
"

LIC_FILES_CHKSUM = "file://cg_xml_${PV}_Manifest.pdf;md5=ba23d76ef3d5ec111b03267105c91bd4"

SRC_URI[cgxmlbin_linux.md5sum] = "9e421f25f6da455b0155570ce0e62628"
SRC_URI[cgxmlbin_linux.sha256sum] = "bbc25f4abe27a8b93bae1d8581c3dee87111c3769c305d086287038ee9038cbf"

BINFILE="cg_xml-v${PV}-Linux-x86-Install"
TI_BIN_UNPK_CMDS=""
TI_BIN_UNPK_ARGS="--prefix ${S} --mode silent"

do_install() {
    CP_ARGS="-Prf --preserve=mode,timestamps --no-preserve=ownership"
    install -d ${D}${CG_XML_INSTALL_DIR_RECIPE}
    cp ${CP_ARGS} ${S}/* ${D}${CG_XML_INSTALL_DIR_RECIPE}
}

FILES_${PN} += "${CG_XML_INSTALL_DIR_RECIPE}"

INSANE_SKIP_${PN} = "arch ldflags file-rdeps"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

BBCLASSEXTEND = "native nativesdk"
