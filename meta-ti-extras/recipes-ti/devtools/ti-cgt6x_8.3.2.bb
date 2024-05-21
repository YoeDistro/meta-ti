DESCRIPTION = "TI DSP Code Generation Tools"
HOMEPAGE = "https://www-a.ti.com/downloads/sds_support/TICodegenerationTools/download.htm"
LICENSE = "(TI-TSPA & Thai-Open-Source-Software-Center) & BSD-3-Clause & BSL-1.0 & Patrick-Powell & AFL-3.0 & MIT & BSD-2-Clause & PD"

LIC_FILES_CHKSUM = "file://ti-cgt-c6000_${PV}/C6000CodeGenerationTools_8.3.x_manifest.html;md5=d06c6f9acebf78df4108a8535396e9f1"
LIC_FILES_CHKSUM:class-target = "file://usr/share/doc/ti/cgt-c6x/C6000CodeGenerationTools_8.3.x_manifest.html;md5=d06c6f9acebf78df4108a8535396e9f1"

PE = "1"

require recipes-ti/includes/ti-unpack.inc

COMPATIBLE_HOST:class-target = "arm.*-linux"

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

BINFILE:class-target = "ti_cgt_c6000_${PV}_armlinuxa8hf_busybox_installer.sh"
BINFILE_NAME:class-target = "cgt6x_arm_installer"

SRC_URI = "http://software-dl.ti.com/codegen/esd/cgt_public_sw/C6000/${PV}/${BINFILE};name=${BINFILE_NAME}"

SRC_URI[cgt6x_x86_installer.md5sum] = "f1f534e6a4bdee6df39e8d0cb458d161"
SRC_URI[cgt6x_x86_installer.sha256sum] = "1fba2a8f5532b33a23677771d686a866171ce7a0e567fed50d900d0ecd2e17e6"

SRC_URI[cgt6x_arm_installer.md5sum] = "425d82308e71202ad004a36b7ec3cec9"
SRC_URI[cgt6x_arm_installer.sha256sum] = "70c387ddde379194fed712087be6cb0ca5f4f0e65d7f29bd7462c38ee011928f"

do_install() {
    install -d ${D}${bindir}
    for binfile in ${S}/ti-cgt-c6000_${PV}/bin/*; do
        install -m 755 ${binfile} ${D}${bindir}
    done

    install -d ${D}${datadir}/ti/cgt-c6x/bin
    for binfile in ${S}/ti-cgt-c6000_${PV}/bin/*; do
        install -m 755 ${binfile} ${D}${datadir}/ti/cgt-c6x/bin
    done

    CP_ARGS="-Prf --preserve=mode,timestamps --no-preserve=ownership"
    cp ${CP_ARGS} ${S}/ti-cgt-c6000_${PV}/include ${D}${datadir}/ti/cgt-c6x/include

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

    for docfile in ${S}/ti-cgt-c6000_${PV}/*.html; do
        install -m 644 ${docfile} ${D}${datadir}/doc/ti/cgt-c6x
    done

    for docfile in ${S}/ti-cgt-c6000_${PV}/*.spdx; do
        install -m 644 ${docfile} ${D}${datadir}/doc/ti/cgt-c6x
    done

}

do_install:class-target() {
    ${UNPACKDIR}/${BINFILE} --prefix ${D}
}

FILES:${PN} += "${datadir}/ti/*"

FILES:${PN}-dbg += "${datadir}/ti/cgt-c6x/bin/.debug"

INSANE_SKIP:${PN} += "staticdev"
INHIBIT_PACKAGE_STRIP_FILES = "${PKGD}${datadir}/ti/cgt-c6x/lib/libc.a"

BBCLASSEXTEND = "native nativesdk"

COMPATIBLE_MACHINE:class-target = "(ti-soc)"
