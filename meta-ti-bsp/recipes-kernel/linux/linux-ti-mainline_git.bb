SECTION = "kernel"
SUMMARY = "Mainline Linux kernel for TI devices"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

inherit kernel

require recipes-kernel/linux/kernel-rdepends.inc
require recipes-kernel/linux/ti-kernel.inc

DEPENDS += "gmp-native libmpc-native"

KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT} \
		      ${EXTRA_DTC_ARGS}"

S = "${WORKDIR}/git"

# 5.15 Mainline version
SRCREV = "8bb7eca972ad531c9b149c0a51ab43a417385813"
PV = "5.15+git${SRCPV}"

# Append to the MACHINE_KERNEL_PR so that a new SRCREV will cause a rebuild
MACHINE_KERNEL_PR:append = "b"
PR = "${MACHINE_KERNEL_PR}"

KERNEL_GIT_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/torvalds/linux.git"
KERNEL_GIT_PROTOCOL = "https"
KERNEL_GIT_BRANCH = "master"
SRC_URI += " \
	${KERNEL_GIT_URI};protocol=${KERNEL_GIT_PROTOCOL};branch=${KERNEL_GIT_BRANCH} \
"

DEFCONFIG_NAME = "multi_v7_defconfig"
DEFCONFIG_NAME:omapl138 = "davinci_all_defconfig"
DEFCONFIG_NAME:k3 = "defconfig"
KERNEL_CONFIG_COMMAND = "oe_runmake -C ${S} O=${B} ${DEFCONFIG_NAME}"

do_shared_workdir:prepend() {
	cd ${B}
	echo >> Module.symvers
}

FILES:${KERNEL_PACKAGE_NAME}-devicetree += "/${KERNEL_IMAGEDEST}/*.itb"
