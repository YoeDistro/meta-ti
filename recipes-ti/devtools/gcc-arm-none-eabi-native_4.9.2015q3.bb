DESCRIPTION = "Baremetal GCC for ARM"
LICENSE = "GPL-3.0-with-GCC-exception & GPLv3"

LIC_FILES_CHKSUM = "file://share/doc/gcc-arm-none-eabi/license.txt;md5=cba77c7fde3ed13e866b04a2f3d93918"

inherit native
require recipes-ti/includes/ti-paths.inc

SRC_URI = "https://launchpad.net/gcc-arm-embedded/4.9/4.9-2015-q3-update/+download/gcc-arm-none-eabi-4_9-2015q3-20150921-linux.tar.bz2;name=gcc-arm-none"

SRC_URI[gcc-arm-none.md5sum] = "8a4a74872830f80c788c944877d3ad8c"
SRC_URI[gcc-arm-none.sha256sum] = "c50078bfbd29e6c15615900e746f4d0acde917338e55860b0f145f57387c12ab"

S = "${WORKDIR}/gcc-arm-none-eabi-4_9-2015q3"

do_install() {
    install -d ${D}${GCC_ARM_NONE_TOOLCHAIN_RECIPE}
    cp -r ${S}/. ${D}${GCC_ARM_NONE_TOOLCHAIN_RECIPE}
}

FILES_${PN} = "${GCC_ARM_NONE_TOOLCHAIN_RECIPE}/*"

INSANE_SKIP_${PN} = "already-stripped"
