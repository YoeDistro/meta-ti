SECTION = "kernel"
SUMMARY = "BeagleBoard.org Linux kernel"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

COMPATIBLE_MACHINE = "beagle.*"

inherit kernel

require recipes-kernel/linux/setup-defconfig.inc
require recipes-kernel/linux/ti-kernel.inc

DEPENDS += "gmp-native libmpc-native"

KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT} ${EXTRA_DTC_ARGS}"

S = "${WORKDIR}/git"

# 6.1.80 version for 32-bit
SRCREV:armv7a = "4ca9ea30768d58c8d4d56d03dd1eaf8c8feb7ef9"
PV:armv7a = "6.1.80+git${SRCPV}"
BRANCH:armv7a = "v6.1.80-ti-r34"

# 6.1.80 version for 64-bit
SRCREV:aarch64 = "977c75e082620f15c06c72bcced30f787c14b390"
PV:aarch64 = "6.1.80+git${SRCPV}"
BRANCH:aarch64 = "v6.1.80-ti-arm64-r49"

SRC_URI = " \
    git://github.com/beagleboard/linux.git;protocol=https;branch=${BRANCH} \
    file://defconfig \
"
