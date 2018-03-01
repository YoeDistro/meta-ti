DESCRIPTION = "GNU debugger for TI C6X DSP."
SECTION = "devel"
LICENSE = "GPLv3+"
LIC_FILES_CHKSUM = "file://debian/copyright;md5=bf0fe2872eb3dfeebb2cbe38206fe81f"

DEPENDS = "ncurses bison-native texinfo flex-native gettext"

COMPATIBLE_MACHINE = "dra7xx|keystone"

PR = "${INC_PR}.1"

SRC_URI_append = " \
    file://init \
    file://0001-coffgen.c-adjust-fall-through-comment-to-work-with-g.patch;striplevel=2 \
    file://0001-reloc.c-add-comments-for-implicit-fallthrough-error-.patch;striplevel=2 \
    file://0001-srec.c-fix-implicit-fallthrough-and-format-overflow-.patch;striplevel=2 \
    file://0001-ihex.c-fix-format-overflow-error-in-gcc7.patch;striplevel=2 \
    file://0001-elf32-tic6x.c-fix-implicit-fallthrough-error-in-gcc7.patch;striplevel=2 \
    file://0001-elf.c-correct-fallthrough-comment-to-recognize-by-gc.patch;striplevel=2 \
    file://0001-elflink.c-fix-implicit-fallthrough-error-in-gcc7.patch;striplevel=2 \
    file://0001-tic6x-dis.c-fix-format-truncation-and-implicit-fallt.patch;striplevel=2 \
"

S = "${WORKDIR}/git/gdbc6x"

inherit update-rc.d
inherit gettext

do_configure () {
    cd ${S}
    ./configure --program-suffix=c6x --target=tic6x-elf-tirtos --host=${HOST_SYS} --prefix=${S}/install_gdb
}

do_install () {
    make install

    # Custom install to prevent conflict with standard GDB.
    install -d ${D}${bindir}
    install -d ${D}${includedir}
    install -m 755  ${S}/install_gdb/bin/gdbc6x ${D}${bindir} 
    cp -rf ${S}/install_gdb/include/* ${D}${includedir}

    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/gdbserverproxy
}

RDEPENDS_${PN} = "gdbserverproxy-module-drv bash"

include gdbc6x.inc

INITSCRIPT_NAME = "gdbserverproxy"
INITSCRIPT_PARAMS = "defaults 95"

PARALLEL_MAKE = ""
