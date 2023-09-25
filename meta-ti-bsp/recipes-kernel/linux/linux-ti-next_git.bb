SECTION = "kernel"
SUMMARY = "Linux-next kernel for TI devices"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

inherit kernel

require recipes-kernel/linux/ti-kernel.inc
include ${@ 'recipes-kernel/linux/ti-kernel-devicetree-prefix.inc' if d.getVar('KERNEL_DEVICETREE_PREFIX') else ''}

DEPENDS += "gmp-native libmpc-native"

KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT} ${EXTRA_DTC_ARGS}"

S = "${WORKDIR}/git"

# 6.6.0-rc3+ version
SRCREV = "6465e260f48790807eef06b583b38ca9789b6072"
PV = "6.6.0-rc3+git${SRCPV}"

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/next/linux-next.git;protocol=https;branch=master"

DEFCONFIG_NAME = "multi_v7_defconfig"
DEFCONFIG_NAME:omapl138 = "davinci_all_defconfig"
DEFCONFIG_NAME:k3 = "defconfig"
KERNEL_CONFIG_COMMAND = "oe_runmake -C ${S} O=${B} ${DEFCONFIG_NAME}"
