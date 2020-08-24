include recipes-ti/devtools/gcc-linaro.inc

PACKAGE_DIR = "gcc-linaro-${PV}-${GCC_ARM_YEAR}-x86_64_aarch64-elf"
S = "${WORKDIR}/${PACKAGE_DIR}"

LIC_CHKSUM = "bc7fad4bba98e7a4cd5ab3042506493c"

inherit native
require recipes-ti/includes/ti-paths.inc

SRC_URI = "https://releases.linaro.org/components/toolchain/binaries/7.2-${GCC_ARM_YEAR}/aarch64-elf/gcc-linaro-${PV}-${GCC_ARM_YEAR}-x86_64_aarch64-elf.tar.xz;name=gcc-linaro-aarch64"

SRC_URI[gcc-linaro-aarch64.md5sum] = "4972f8943820bdd2f577f498de78964b"
SRC_URI[gcc-linaro-aarch64.sha256sum] = "30fb7d876bcb982c502057c593d9c1f11b35d5158a26d986718e2b998388c4c8"

do_install() {
    install -d ${D}${GCC_LINARO_BAREMETAL_AARCH64_TOOLCHAIN_RECIPE}
    cp -r ${S}/. ${D}${GCC_LINARO_BAREMETAL_AARCH64_TOOLCHAIN_RECIPE}
}

FILES_${PN} = "${GCC_LINARO_BAREMETAL_AARCH64_TOOLCHAIN_RECIPE}/*"

INSANE_SKIP_${PN} = "already-stripped"
