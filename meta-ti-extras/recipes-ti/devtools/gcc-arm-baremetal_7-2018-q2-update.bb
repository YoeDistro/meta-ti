SUMMARY = "Baremetal GCC for ARM"
LICENSE = "GPL-3.0-with-GCC-exception & GPL-3.0-only"

LIC_FILES_CHKSUM = "file://share/doc/gcc-arm-none-eabi/license.txt;md5=f77466c63f5787f4bd669c402aabe061"

require recipes-ti/includes/ti-paths.inc

SRC_URI = "https://developer.arm.com/-/media/Files/downloads/gnu-rm/7-2018q2/gcc-arm-none-eabi-7-2018-q2-update-linux.tar.bz2;name=gcc-arm-none"

SRC_URI[gcc-arm-none.md5sum] = "299ebd3f1c2c90930d28ab82e5d8d6c0"
SRC_URI[gcc-arm-none.sha256sum] = "bb17109f0ee697254a5d4ae6e5e01440e3ea8f0277f2e8169bf95d07c7d5fe69"

S = "${UNPACKDIR}/gcc-arm-none-eabi-7-2018-q2-update"

# only x86_64 is supported
COMPATIBLE_HOST = "x86_64.*-linux"
COMPATIBLE_HOST:class-target = "null"

do_install() {
    install -d ${D}${GCC_ARM_NONE_TOOLCHAIN_RECIPE}
    cp -r ${S}/. ${D}${GCC_ARM_NONE_TOOLCHAIN_RECIPE}
}

FILES:${PN} = "${GCC_ARM_NONE_TOOLCHAIN_RECIPE}/*"

INSANE_SKIP:${PN} = "already-stripped libdir staticdev build-deps file-rdeps arch"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

BBCLASSEXTEND = "native nativesdk"
