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

# 5.10.162 version for 32-bit
SRCREV:armv7a = "982fde4eb381f98ec8be946e8d33dd0c9f9416ab"
PV:armv7a = "5.10.162+git${SRCPV}"
BRANCH:armv7a = "v5.10.162-ti-r59"

# 5.10.162 version for 64-bit
SRCREV:aarch64 = "a2f5d5746b6c389e58d20fda0a0fa88403da428b"
PV:aarch64 = "5.10.162+git${SRCPV}"
BRANCH:aarch64 = "v5.10.162-ti-arm64-r99"

SRC_URI = " \
    git://github.com/beagleboard/linux.git;protocol=https;branch=${BRANCH} \
    file://defconfig \
"

SRC_URI:append:armv7a = " file://0001-defconfig-switch-default-kernel-compression-to-LZMA.patch"
