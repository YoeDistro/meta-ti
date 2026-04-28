SUMMARY = "Utilities and example programs for use with XDP"
HOMEPAGE = "https://github.com/xdp-project/xdp-tools"
LICENSE = "GPL-2.0-or-later & LGPL-2.1-or-later & BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9ee53f8d06bbdb4c11b1557ecc4f8cd5 \
                    file://LICENSES/GPL-2.0;md5=994331978b428511800bfbd17eea3001 \
                    file://LICENSES/LGPL-2.1;md5=b370887980db5dd40659b50909238dbd \
                    file://LICENSES/BSD-2-Clause;md5=5d6306d1b08f8df623178dfd81880927"

DEPENDS += " libbpf zlib elfutils libpcap"
DEPENDS += " clang-cross-${TARGET_ARCH} bpftool-native"

SRC_URI = "git://github.com/xdp-project/xdp-tools.git;protocol=https;branch=main \
            file://0001-configure-skip-toolchain-checks.patch \
            file://0002-Makefile-It-does-not-detect-libbpf-header-from-sysro.patch \
            file://0003-Makefile-fix-KeyError-failure.patch \
            file://0004-Makefile-fix-libxdp.pc-error.patch \
            file://0001-defines.mk-Add-missing-prefix-map-settings-for-OE-bu.patch \
            file://0001-xdpsock-Fix-32bit-compile-error.patch \
          "

SRCREV = "84906a0fe98cbb5e5eaa2c888c50a1ab32d5d0b7"

inherit pkgconfig

EXTRA_OEMAKE += " PREFIX=${D}${prefix} LIBDIR=${D}${libdir} BUILD_STATIC_ONLY=1 PRODUCTION=1 V=1"
EXTRA_OEMAKE += " OE_BPF_OBJECT_DIR=${libdir}/bpf"
EXTRA_OEMAKE += " CLANG="${TARGET_PREFIX}clang --sysroot=${RECIPE_SYSROOT}""

CFLAGS += "-fPIC"

export STAGING_INCDIR

do_compile:prepend:aarch64 () {
    mkdir -p ${S}/headers/asm
    cp ${RECIPE_SYSROOT}/usr/include/asm/bitsperlong-64.h ${S}/headers/asm/bitsperlong-32.h
    cp ${RECIPE_SYSROOT}/usr/include/asm/byteorder-64.h ${S}/headers/asm/byteorder-32.h
    cp ${RECIPE_SYSROOT}/usr/include/asm/posix_types-64.h ${S}/headers/asm/posix_types-32.h
    cp ${RECIPE_SYSROOT}/usr/include/asm/swab-64.h ${S}/headers/asm/swab-32.h
    cp ${RECIPE_SYSROOT}/usr/include/asm/types-64.h ${S}/headers/asm/types-32.h
}

do_install() {
    oe_runmake install
}

FILES:${PN} += "${datadir}/xdp-tools/* \
                ${nonarch_base_libdir}/bpf/* \
               "

RDEPENDS:${PN} += "bash"

