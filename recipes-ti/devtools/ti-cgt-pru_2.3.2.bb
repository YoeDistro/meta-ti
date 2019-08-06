DESCRIPTION = "TI PRU Code Generation Tools"
HOMEPAGE = "https://www-a.ti.com/downloads/sds_support/TICodegenerationTools/download.htm"
LICENSE = "(TI-TSPA & Thai-Open-Source-Software-Center) & BSD-3-Clause & BSL-1.0 & Hewlett-Packard & AFL-3.0 & MIT & BSD-2-Clause & PD"

LIC_FILES_CHKSUM = "file://ti-cgt-pru_${PV}/PRU_Code_Generation_Tools_2.3.x_manifest.html;md5=e22f9d8240f7cca0c0aa5242d9ffa5bc"
LIC_FILES_CHKSUM_class-target = "file://usr/share/doc/ti/cgt-pru/PRU_Code_Generation_Tools_2.3.x_manifest.html;md5=e22f9d8240f7cca0c0aa5242d9ffa5bc"

require recipes-ti/includes/ti-paths.inc
require recipes-ti/includes/ti-unpack.inc

BINFILE = "ti_cgt_pru_${PV}_linux_installer_x86.bin"
BINFILE_NAME = "cgt-pru-x86"
TI_BIN_UNPK_ARGS = "--prefix ${S}"
TI_BIN_UNPK_CMDS = ""

BINFILE_class-target = "ti_cgt_pru_${PV}_armlinuxa8hf_busybox_installer.sh"
BINFILE_NAME_class-target = "cgt-pru-arm"

SRC_URI = "http://software-dl.ti.com/codegen/esd/cgt_public_sw/PRU/${PV}/${BINFILE};name=${BINFILE_NAME}"

SRC_URI[cgt-pru-x86.md5sum] = "270a0ecca383f66cad3a0e2a738c6ab0"
SRC_URI[cgt-pru-x86.sha256sum] = "af1178a059d46d67d5672df053dd2fbebb0b89a513b17b8739dbe915b4a5d71a"

SRC_URI[cgt-pru-arm.md5sum] = "fbf10083af1b0e5d69b018126603bb58"
SRC_URI[cgt-pru-arm.sha256sum] = "75ac84775a5624ea7e4fad4efc1acb7e869c79160550d48ba780068a3dce879c"

do_install() {
    install -d ${D}${TI_CGT_PRU_INSTALL_DIR_RECIPE}
    cp -r ${S}/ti-cgt-pru_${PV}/. \
          ${D}${TI_CGT_PRU_INSTALL_DIR_RECIPE}/
}

do_install_class-target() {
    ${WORKDIR}/${BINFILE} --prefix ${D}
}

FILES_${PN} += "${datadir}/ti/*"

FILES_${PN}-dbg = "${TI_CGT_PRU_INSTALL_DIR_RECIPE}/bin/.debug \
                   ${TI_CGT_PRU_INSTALL_DIR_RECIPE}/lib/.debug \
"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

INSANE_SKIP_${PN} += "arch staticdev already-stripped file-rdeps"

BBCLASSEXTEND = "native nativesdk"
