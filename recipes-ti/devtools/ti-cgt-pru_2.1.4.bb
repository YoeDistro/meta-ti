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

SRC_URI[cgt-pru-x86.md5sum] = "496b32b9804cf5b5b293dd938f7c5339"
SRC_URI[cgt-pru-x86.sha256sum] = "c80dbf35e7401f4c122ff25cc2f6b5db496607782fb4583cee8663a2763f4472"

SRC_URI[cgt-pru-arm.md5sum] = "b13bf0cdf484102cdfcb92a81b8092a2"
SRC_URI[cgt-pru-arm.sha256sum] = "c125bbe4e805b830e5d746ade929dc1f202f25a6863d5005ed97cc5a82e26754"

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
