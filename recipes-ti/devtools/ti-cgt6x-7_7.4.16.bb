SUMMARY = "TI DSP Code Generation Tools"
DESCRIPTION = "Texas Instrument (TI) Code Generation Tools are custom \
utilities targeted for TI embedded processors. This Digital Signal \
Processor (DSP) suite contains tools needed to create and debug \
applications for the C6000 DSP family. This includes tools such as: \
compiler, linker, assembler, etc. This also includes C runtime \
libraries and standard header files needed to produce a working DSP application."

HOMEPAGE = "https://www-a.ti.com/downloads/sds_support/TICodegenerationTools/download.htm"
LICENSE = "TI-TSPA"

LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=b6311962635a4f15630e36ec2d875eca"

S = "${WORKDIR}/c6000_7.4.16"

require recipes-ti/includes/ti-paths.inc
require recipes-ti/includes/ti-unpack.inc

BINFILE = "ti_cgt_c6000_7.4.16_linux_installer_x86.bin"
BINFILE_NAME = "cgt6x_7.4.16_x86_installer"
TI_BIN_UNPK_ARGS = "--prefix ${WORKDIR} --mode unattended"
TI_BIN_UNPK_CMDS=""

SRC_URI = "http://install.source.dir.local/${BINFILE};name=${BINFILE_NAME}"

SRC_URI[cgt6x_7.4.16_x86_installer.md5sum] = "21ca55c5b1f6b2d8d4fb7570d5eb5513"
SRC_URI[cgt6x_7.4.16_x86_installer.sha256sum] = "baa0d1ef20397383f99f45068a6d160963a01419d42fbbb851263b54c91df82f"

# only x86_64 is supported
COMPATIBLE_HOST = "x86_64.*-linux"
COMPATIBLE_HOST_class-target = "null"

do_install() {
    install -d ${D}/${TI_CGT6X_7_INSTALL_DIR_RECIPE}
    cp -rP --preserve=mode,links,timestamps --no-preserve=ownership ${WORKDIR}/c6000_7.4.16/. ${D}/${TI_CGT6X_7_INSTALL_DIR_RECIPE}
}

FILES_${PN} += "${TI_CGT6X_7_INSTALL_DIR_RECIPE}"

INSANE_SKIP_${PN} += "arch staticdev textrel"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

BBCLASSEXTEND = "native nativesdk"
