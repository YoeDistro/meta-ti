SECTION = "kernel"
SUMMARY = "Mainline Linux kernel for TI devices"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

inherit kernel

require recipes-kernel/linux/ti-kernel.inc

DEPENDS += "gmp-native libmpc-native"

KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT} ${EXTRA_DTC_ARGS}"

S = "${WORKDIR}/git"

# 6.6 Mainline version
SRCREV = "ffc253263a1375a65fa6c9f62a893e9767fbebfa"
PV = "6.6"

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/torvalds/linux.git;protocol=https;branch=master"

DEFCONFIG_NAME = "multi_v7_defconfig"
DEFCONFIG_NAME:omapl138 = "davinci_all_defconfig"
DEFCONFIG_NAME:k3 = "defconfig"
KERNEL_CONFIG_COMMAND = "oe_runmake -C ${S} O=${B} ${DEFCONFIG_NAME}"
