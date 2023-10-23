SECTION = "kernel"
SUMMARY = "BeagleBoard.org Linux kernel"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

COMPATIBLE_MACHINE = "beagle.*"

inherit kernel

require recipes-kernel/linux/setup-defconfig.inc
require recipes-kernel/linux/ti-kernel.inc

# BB.org hasn't switched to "vendored" DTB layout by default yet
KERNEL_DTBVENDORED = "0"

DEPENDS += "gmp-native libmpc-native"

KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT} ${EXTRA_DTC_ARGS}"

S = "${WORKDIR}/git"

# 6.1.33 version for 32-bit
SRCREV:armv7a = "feb1c153a9693d44dec5772d134bd2c7986ff95d"
PV:armv7a = "6.1.33+git${SRCPV}"
BRANCH:armv7a = "v6.1.33-ti-r7"

# 6.1.46 version for 64-bit
SRCREV:aarch64 = "0a1e93a38c699fdd3c629d57f5cd015f90ab0d36"
PV:aarch64 = "6.1.46+git${SRCPV}"
BRANCH:aarch64 = "v6.1.46-ti-arm64-r9"

SRC_URI = "git://github.com/beagleboard/linux.git;protocol=https;branch=${BRANCH} \
           file://defconfig \
"

FILES:${KERNEL_PACKAGE_NAME}-devicetree += " \
    /${KERNEL_DTBDEST}/*/overlays/*.dtb \
    /${KERNEL_DTBDEST}/*/overlays/*.dtbo \
"
