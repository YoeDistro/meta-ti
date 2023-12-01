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

# 6.1.46 version for 32-bit
SRCREV:armv7a = "0df74a0c083222077e8def3ec7e52c75f7eff2f0"
PV:armv7a = "6.1.46+git${SRCPV}"
BRANCH:armv7a = "v6.1.46-ti-r13"

# 6.1.46 version for 64-bit
SRCREV:aarch64 = "f47f74d11b19d8ae2f146de92c258f40e0930d86"
PV:aarch64 = "6.1.46+git${SRCPV}"
BRANCH:aarch64 = "v6.1.46-ti-arm64-r13"

SRC_URI = "git://github.com/beagleboard/linux.git;protocol=https;branch=${BRANCH} \
           file://defconfig \
"

FILES:${KERNEL_PACKAGE_NAME}-devicetree += " \
    /${KERNEL_DTBDEST}/*/overlays/*.dtb \
    /${KERNEL_DTBDEST}/*/overlays/*.dtbo \
"
