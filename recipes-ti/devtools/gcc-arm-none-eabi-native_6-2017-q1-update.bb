DESCRIPTION = "Baremetal GCC for ARM"
LICENSE = "GPL-3.0-with-GCC-exception & GPLv3"

LIC_FILES_CHKSUM = "file://share/doc/gcc-arm-none-eabi/license.txt;md5=c224e429f53a1a6ce70bf8986ea2990b"

inherit native
require recipes-ti/includes/ti-paths.inc

SRC_URI = "https://developer.arm.com/-/media/Files/downloads/gnu-rm/6_1-2017q1/gcc-arm-none-eabi-6-2017-q1-update-linux.tar.bz2;name=gcc-arm-none"

SRC_URI[gcc-arm-none.md5sum] = "30004c24f4632bc594952462bb0cd1c9"
SRC_URI[gcc-arm-none.sha256sum] = "e7aad2579f02e3b095c6d7899ca5e6a70cfa9b8a8cbd6abd868da849d416c2eb"

S = "${WORKDIR}/gcc-arm-none-eabi-6-2017-q1-update"

do_install() {
    install -d ${D}${GCC_ARM_NONE_TOOLCHAIN_RECIPE}
    cp -r ${S}/. ${D}${GCC_ARM_NONE_TOOLCHAIN_RECIPE}
}

FILES_${PN} = "${GCC_ARM_NONE_TOOLCHAIN_RECIPE}/*"

INSANE_SKIP_${PN} = "already-stripped"
