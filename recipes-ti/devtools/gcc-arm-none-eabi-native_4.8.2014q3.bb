DESCRIPTION = "Baremetal GCC for ARM"
LICENSE = "GPL-3.0-with-GCC-exception & GPLv3"

LIC_FILES_CHKSUM = "file://share/doc/gcc-arm-none-eabi/license.txt;md5=cba77c7fde3ed13e866b04a2f3d93918"

inherit native
require recipes-ti/includes/ti-paths.inc

SRC_URI = "https://launchpad.net/gcc-arm-embedded/4.8/4.8-2014-q3-update/+download/gcc-arm-none-eabi-4_8-2014q3-20140805-linux.tar.bz2;name=gcc-arm-none"

SRC_URI[gcc-arm-none.md5sum] = "acc8c8ff45f8801e2155934214309a87"
SRC_URI[gcc-arm-none.sha256sum] = "e33d7546de1e02844239c72b3ef5563f472fcd6b4637419d513770ae13f551c0"

S = "${WORKDIR}/gcc-arm-none-eabi-4_8-2014q3"

do_install() {
    install -d ${D}${GCC_ARM_NONE_TOOLCHAIN_RECIPE}
    cp -r ${S}/. ${D}${GCC_ARM_NONE_TOOLCHAIN_RECIPE}
}

FILES_${PN} = "${GCC_ARM_NONE_TOOLCHAIN_RECIPE}/*"

INSANE_SKIP_${PN} = "already-stripped"
