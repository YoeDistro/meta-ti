SUMMARY = "TI DSP Code Generation Tools"
DESCRIPTION = "Texas Instruments (TI) Code Generation Tools are custom \
utilities targeted for TI embedded processors. This Digital Signal \
Processor (DSP) suite contains tools needed to create and debug \
applications for the C7000 DSP family. This includes tools such as: \
compiler, linker, assembler, etc. This also includes C runtime \
libraries and standard header files needed to produce a working DSP application."
HOMEPAGE = "https://www-a.ti.com/downloads/sds_support/TICodegenerationTools/download.htm"
LICENSE = "(TI-TSPA & Thai-Open-Source-Software-Center) & BSD-3-Clause & BSL-1.0 & Patrick-Powell & AFL-3.0 & MIT & BSD-2-Clause & PD"

LIC_FILES_CHKSUM = "file://ti-cgt-c7000_${PV}/C7000_1.0.x_CodeGenerationTools_Manifest.html;md5=3074e433c5d52657076d1d138dfbdaf8"

require recipes-ti/includes/ti-unpack.inc
require recipes-ti/includes/ti-paths.inc

# only x86_64 is supported
COMPATIBLE_HOST = "x86_64.*-linux"
COMPATIBLE_HOST_class-target = "null"

BINFILE = "ti_cgt_c7000_${PV}_linux_installer_x86.bin"
BINFILE_NAME = "cgt7x_x86_installer"

# Please note, "install.source.dir.local" is not a real URL, below files need to be pre-downloaded
SRC_URI = "http://install.source.dir.local/${BINFILE};name=${BINFILE_NAME}"

TI_BIN_UNPK_ARGS = "--prefix ${S}"
TI_BIN_UNPK_CMDS = ""

SRC_URI[cgt7x_x86_installer.md5sum]    = "feb668b4213403c661909adcf65d2ba8"
SRC_URI[cgt7x_x86_installer.sha256sum] = "4a3954c973622c00a9b91fa90473cf921f4b763300b0e7c32735304867856ab8"

S = "${WORKDIR}/c7000_${PV}"

do_install() {
    install -d ${D}/${TI_CGT7X_INSTALL_DIR_RECIPE}
    cp -rP --preserve=mode,links,timestamps --no-preserve=ownership ${WORKDIR}/c7000_${PV}/ti-cgt-c7000_${PV}/. ${D}/${TI_CGT7X_INSTALL_DIR_RECIPE}
}


FILES_${PN} += "${TI_CGT7X_INSTALL_DIR_RECIPE}"

INSANE_SKIP_${PN} += "arch staticdev textrel"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

BBCLASSEXTEND = "native nativesdk"
