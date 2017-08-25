DESCRIPTION = "TI PRU Code Generation Tools"
HOMEPAGE = "https://www-a.ti.com/downloads/sds_support/TICodegenerationTools/download.htm"
LICENSE = "(TI-TSPA & Thai-Open-Source-Software-Center) & BSD-3-Clause & BSL-1.0 & Hewlett-Packard & AFL-3.0 & MIT & BSD-2-Clause & PD"

LIC_FILES_CHKSUM = "file://ti-cgt-pru_${PV}/PRU_Code_Generation_Tools_2.1.x_manifest.html;md5=d7fea45a7968939ff953ae8a1e6719f4"
LIC_FILES_CHKSUM_class-target = "file://usr/share/doc/ti/cgt-pru/PRU_Code_Generation_Tools_2.1.x_manifest.html;md5=d7fea45a7968939ff953ae8a1e6719f4"

require recipes-ti/includes/ti-paths.inc
require recipes-ti/includes/ti-unpack.inc

BINFILE = "ti_cgt_pru_${PV}_linux_installer_x86.bin"
BINFILE_NAME = "cgt-pru-x86"
TI_BIN_UNPK_ARGS = "--prefix ${S}"
TI_BIN_UNPK_CMDS = ""

BINFILE_class-target = "ti_cgt_pru_${PV}_armlinuxa8hf_busybox_installer.sh"
BINFILE_NAME_class-target = "cgt-pru-arm"

SRC_URI = "http://software-dl.ti.com/codegen/esd/cgt_public_sw/PRU/${PV}/${BINFILE};name=${BINFILE_NAME}"

SRC_URI[cgt-pru-x86.md5sum] = "06ac11b90a55ee787ac20fcca9a27013"
SRC_URI[cgt-pru-x86.sha256sum] = "0f5b965ae1fda818ce5a81f1adbdae0a65047c6ae6cff1869e08e678122f8dff"

SRC_URI[cgt-pru-arm.md5sum] = "bedda3b31d8918d5d1d3c6d886821d0a"
SRC_URI[cgt-pru-arm.sha256sum] = "f2711478f9160a73660a2b2f316ad446bc481141bb9889f1001441beccce3584"

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

INSANE_SKIP_${PN} += "arch staticdev already-stripped"

BBCLASSEXTEND = "native nativesdk"
