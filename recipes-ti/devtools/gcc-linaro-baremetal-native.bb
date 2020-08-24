include recipes-ti/devtools/gcc-linaro.inc

PACKAGE_DIR = "gcc-linaro-${PV}-${GCC_ARM_YEAR}-x86_64_arm-eabi"
S = "${WORKDIR}/${PACKAGE_DIR}"

LIC_CHKSUM = "bc7fad4bba98e7a4cd5ab3042506493c"

inherit native
require recipes-ti/includes/ti-paths.inc

SRC_URI = "https://releases.linaro.org/components/toolchain/binaries/7.2-${GCC_ARM_YEAR}/arm-eabi/gcc-linaro-${PV}-${GCC_ARM_YEAR}-x86_64_arm-eabi.tar.xz;name=gcc-linaro"

SRC_URI[gcc-linaro.md5sum] = "9a4a39cfc78c84375b1160aa93587aed"
SRC_URI[gcc-linaro.sha256sum] = "45fc4f90af2e2e9c3197a275f6005ef5639f7a1889fa3eb24c3125ccd70fcd3d"

do_install() {
    install -d ${D}${GCC_LINARO_BAREMETAL_TOOLCHAIN_RECIPE}
    cp -r ${S}/. ${D}${GCC_LINARO_BAREMETAL_TOOLCHAIN_RECIPE}
}

FILES_${PN} = "${GCC_LINARO_BAREMETAL_TOOLCHAIN_RECIPE}/*"

INSANE_SKIP_${PN} = "already-stripped"
