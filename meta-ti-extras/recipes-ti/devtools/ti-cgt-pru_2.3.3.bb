DESCRIPTION = "TI PRU Code Generation Tools"
HOMEPAGE = "https://www-a.ti.com/downloads/sds_support/TICodegenerationTools/download.htm"
LICENSE = "(TI-TSPA & Thai-Open-Source-Software-Center) & BSD-3-Clause & BSL-1.0 & Hewlett-Packard & AFL-3.0 & MIT & BSD-2-Clause & PD"

LIC_FILES_CHKSUM = "file://${UNPACKDIR}/ti-cgt-pru_${PV}/PRU_Code_Generation_Tools_2.3.x_manifest.html;md5=e22f9d8240f7cca0c0aa5242d9ffa5bc"
LIC_FILES_CHKSUM:class-target = "file://usr/share/doc/ti/cgt-pru/PRU_Code_Generation_Tools_2.3.x_manifest.html;md5=e22f9d8240f7cca0c0aa5242d9ffa5bc"

require recipes-ti/includes/ti-paths.inc
require recipes-ti/includes/ti-unpack.inc

BINFILE = "ti_cgt_pru_${PV}_linux_installer_x86.bin"
BINFILE_NAME = "cgt-pru-x86"
TI_BIN_UNPK_ARGS = "--prefix ${UNPACKDIR}"
TI_BIN_UNPK_CMDS = ""

BINFILE:class-target = "ti_cgt_pru_${PV}_armlinuxa8hf_busybox_installer.sh"
BINFILE_NAME:class-target = "cgt-pru-arm"

SRC_URI = "http://software-dl.ti.com/codegen/esd/cgt_public_sw/PRU/${PV}/${BINFILE};name=${BINFILE_NAME}"

SRC_URI[cgt-pru-x86.md5sum] = "abb76fac986993aafaf467915985ec4f"
SRC_URI[cgt-pru-x86.sha256sum] = "1f1405167214f2c0ef848591b17a7799fdcd9f55f11bc90db9ace3490d426215"

SRC_URI[cgt-pru-arm.md5sum] = "648a6d7d8162fd6a89f381c7b974e6b0"
SRC_URI[cgt-pru-arm.sha256sum] = "8390cb77b46b728ce2940595b81406f76d86dfed58c21258e3206a7c1232ccf2"

S = "${WORKDIR}/sources"
UNPACKDIR = "${S}"

do_install() {
    install -d ${D}${TI_CGT_PRU_INSTALL_DIR_RECIPE}
    cp -r ${S}/ti-cgt-pru_${PV}/. \
          ${D}${TI_CGT_PRU_INSTALL_DIR_RECIPE}/
    rm ${D}${TI_CGT_PRU_INSTALL_DIR_RECIPE}/*installer_install.log
}

do_install:class-target() {
    ${S}/${BINFILE} --prefix ${D}
}

FILES:${PN} += "${datadir}/ti/*"

FILES:${PN}-dbg = "${TI_CGT_PRU_INSTALL_DIR_RECIPE}/bin/.debug \
                   ${TI_CGT_PRU_INSTALL_DIR_RECIPE}/lib/.debug \
"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

INSANE_SKIP:${PN} += "arch staticdev already-stripped file-rdeps"

BBCLASSEXTEND = "native nativesdk"
