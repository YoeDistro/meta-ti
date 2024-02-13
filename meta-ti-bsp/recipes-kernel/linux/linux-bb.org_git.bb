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

# 6.1.69 version for 32-bit
SRCREV:armv7a = "a1f26a19581970f76220c68c438981796ce5b767"
PV:armv7a = "6.1.69+git${SRCPV}"
BRANCH:armv7a = "v6.1.69-ti-r22"

# 6.1.69 version for 64-bit
SRCREV:aarch64 = "bc76b5d278271f66a6faeb2d09cb09fc6c57dd3c"
PV:aarch64 = "6.1.69+git${SRCPV}"
BRANCH:aarch64 = "v6.1.69-ti-arm64-r24"

SRC_URI = " \
    git://github.com/beagleboard/linux.git;protocol=https;branch=${BRANCH} \
    file://defconfig \
"
