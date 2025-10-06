#
# Jailhouse settings
#
COMPATIBLE_MACHINE:tie-jailhouse = "am62xx|am62pxx|am62lxx"

BRANCH:tie-jailhouse:bsp-ti-6_12 = "ti-u-boot-2025.01-jailhouse"

SRCREV_uboot:tie-jailhouse:bsp-ti-6_12 = "e718bbcec3ebf663c021839753034a224be4cc53"

UBOOT_GIT_URI:tie-jailhouse = "git://git.ti.com/git/processor-sdk/u-boot.git"

#
# Test builds settings
#
TI_UPSTREAM_TOOLS_INCLUDE ?= ""
TI_UPSTREAM_TOOLS_INCLUDE:tie-test-builds = "recipes-ti/ti-upstream-tools/ti-upstream-tools.inc"
require ${TI_UPSTREAM_TOOLS_INCLUDE}

SRC_URI:append:tie-test-builds = " ${TI_UPSTREAM_TOOLS_SRC_URI}"
SRCREV_ti-upstream-tools:tie-test-builds = "${TI_UPSTREAM_TOOLS_SRCREV}"
SRCREV_FORMAT:tie-test-builds = "uboot_ti-upstream-tools"

UBOOT_TI_TEST_BUILDS_MAKE_OPTS:tie-test-builds = "CONFIG_DEFCONFIG_PATH=${B}/test-build-configs"

do_configure:prepend:tie-test-builds() {
    ${UNPACKDIR}/ti-upstream-tools/uboot-configs/generate_configs.sh \
        --uboot-src "${S}" \
        --output-dir "${B}/test-build-configs" \
        --yocto-machine "${MACHINE}"
}
