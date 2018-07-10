DESCRIPTION = "TI DSP Code Generation Tools"
HOMEPAGE = "https://www-a.ti.com/downloads/sds_support/TICodegenerationTools/download.htm"
LICENSE = "(TI-TSPA & Thai-Open-Source-Software-Center) & BSD-3-Clause & BSL-1.0 & Hewlett-Packard & AFL-3.0 & MIT & BSD-2-Clause & PD"

LIC_FILES_CHKSUM = "file://ti-cgt-c6000_${PV}/C6000_8.2.x_CodeGenerationTools_Manifest.htm;md5=66147c95fc5d3f900b73414f8258c91e"
LIC_FILES_CHKSUM_class-target = "file://usr/share/doc/ti/cgt-c6x/C6000_8.2.x_CodeGenerationTools_Manifest.htm;md5=66147c95fc5d3f900b73414f8258c91e"

PE = "1"

require recipes-ti/includes/ti-unpack.inc

COMPATIBLE_HOST_class-target = "arm.*-linux"

# For now we only have hardfp version for target class
python __anonymous() {
    c = d.getVar("CLASSOVERRIDE")

    if c == "class-target":
        tunes = d.getVar("TUNE_FEATURES")
        if not tunes:
            return
        pkgn = d.getVar("PN")
        pkgv = d.getVar("PV")
        if "callconvention-hard" not in tunes:
            bb.warn("%s-%s ONLY supports hardfp mode for now" % (pkgn, pkgv))
            raise bb.parse.SkipPackage("%s-%s ONLY supports hardfp mode for now" % (pkgn, pkgv))
}


BINFILE = "ti_cgt_c6000_${PV}_linux_installer_x86.bin"
BINFILE_NAME = "cgt6x_x86_installer"
TI_BIN_UNPK_ARGS = "--prefix ${S}"
TI_BIN_UNPK_CMDS = ""

BINFILE_class-target = "ti_cgt_c6000_${PV}_armlinuxa8hf_busybox_installer.sh"
BINFILE_NAME_class-target = "cgt6x_arm_installer"

SRC_URI = "http://software-dl.ti.com/codegen/esd/cgt_public_sw/C6000/${PV}/${BINFILE};name=${BINFILE_NAME}"

SRC_URI[cgt6x_x86_installer.md5sum] = "548c841b231c2fbd954218d4e370c995"
SRC_URI[cgt6x_x86_installer.sha256sum] = "f269e51eb29de2efd32b3ea93beebd7b00a1a156c923be1a33ba7d785c4c6a24"

SRC_URI[cgt6x_arm_installer.md5sum] = "56f5e5464ab87af517a6cdd5fed06f3f"
SRC_URI[cgt6x_arm_installer.sha256sum] = "4b2e605a4afc3d4d0ac74013ee32496541abf093aedd70bc7ef040597933b1ea"

do_install() {
    install -d ${D}${bindir}
    for binfile in ${S}/ti-cgt-c6000_${PV}/bin/*; do
        install -m 755 ${binfile} ${D}${bindir}
    done

    install -d ${D}${datadir}/ti/cgt-c6x/bin
    for binfile in ${S}/ti-cgt-c6000_${PV}/bin/*; do
        install -m 755 ${binfile} ${D}${datadir}/ti/cgt-c6x/bin
    done

    install -d ${D}${datadir}/ti/cgt-c6x/include
    for includefile in ${S}/ti-cgt-c6000_${PV}/include/*; do
        install -m 644 ${includefile} ${D}${datadir}/ti/cgt-c6x/include
    done

    install -d ${D}${datadir}/ti/cgt-c6x/lib
    for libfile in ${S}/ti-cgt-c6000_${PV}/lib/*.a; do
        install -m 644 ${libfile} ${D}${datadir}/ti/cgt-c6x/lib
    done

    for libfile in ${S}/ti-cgt-c6000_${PV}/lib/*.cmd; do
        install -m 644 ${libfile} ${D}${datadir}/ti/cgt-c6x/lib
    done

    for libfile in ${S}/ti-cgt-c6000_${PV}/lib/*.lib; do
        install -m 644 ${libfile} ${D}${datadir}/ti/cgt-c6x/lib
    done

    install -d ${D}${datadir}/ti/cgt-c6x/lib/src
    for srcfile in ${S}/ti-cgt-c6000_${PV}/lib/src/*; do
        install -m 644 ${srcfile} ${D}${datadir}/ti/cgt-c6x/lib/src
    done

    install -d ${D}${datadir}/man/man1
    for manfile in ${S}/ti-cgt-c6000_${PV}/man/man1/*; do
        if [ -e ${manfile} ]; then
            install -m 644 ${manfile} ${D}${datadir}/man/man1
        fi
    done

    install -d ${D}${datadir}/doc/ti/cgt-c6x
    for docfile in ${S}/ti-cgt-c6000_${PV}/*.txt; do
        install -m 644 ${docfile} ${D}${datadir}/doc/ti/cgt-c6x
    done

    for docfile in ${S}/ti-cgt-c6000_${PV}/*.htm; do
        install -m 644 ${docfile} ${D}${datadir}/doc/ti/cgt-c6x
    done

    for docfile in ${S}/ti-cgt-c6000_${PV}/*.spdx; do
        install -m 644 ${docfile} ${D}${datadir}/doc/ti/cgt-c6x
    done

    for docfile in ${S}/ti-cgt-c6000_${PV}/doc/*; do
        install -m 644 ${docfile} ${D}${datadir}/doc/ti/cgt-c6x
    done

}

do_install_class-target() {
    ${WORKDIR}/${BINFILE} --prefix ${D}
}

FILES_${PN} += "${datadir}/ti/*"

FILES_${PN}-dbg += "${datadir}/ti/cgt-c6x/bin/.debug"

INSANE_SKIP_${PN} += "staticdev"

BBCLASSEXTEND = "native nativesdk"
