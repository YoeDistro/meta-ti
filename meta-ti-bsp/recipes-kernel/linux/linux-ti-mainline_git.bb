SECTION = "kernel"
SUMMARY = "Mainline Linux kernel for TI devices (with ti-upstream-tools)"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

inherit kernel

DEFCONFIG_BUILDER = "${S}/ti-upstream-tools/config/defconfig_builder.sh"
require recipes-kernel/linux/setup-defconfig.inc
require recipes-kernel/linux/kernel-rdepends.inc
require recipes-kernel/linux/ti-kernel.inc

DEPENDS += "gmp-native libmpc-native"

KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT} \
		      ${EXTRA_DTC_ARGS}"

S = "${WORKDIR}/git"

BRANCH = "master"
TOOLS_BRANCH = "master"

# 5.15 Mainline version
SRCREV = "8bb7eca972ad531c9b149c0a51ab43a417385813"
PV = "5.15+git${SRCPV}"

# Append to the MACHINE_KERNEL_PR so that a new SRCREV will cause a rebuild
MACHINE_KERNEL_PR:append = "b"
PR = "${MACHINE_KERNEL_PR}"

KERNEL_GIT_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/torvalds/linux.git"
KERNEL_GIT_PROTOCOL = "https"
SRC_URI += " \
    ${KERNEL_GIT_URI};protocol=${KERNEL_GIT_PROTOCOL};branch=${BRANCH};name=linux \
    git://git.ti.com/git/ti-linux-kernel/ti-upstream-tools.git;branch=${TOOLS_BRANCH};protocol=${KERNEL_GIT_PROTOCOL};name=ti-upstream-tools;destsuffix=git/ti-upstream-tools \
    file://defconfig \
"

SRCREV_ti-upstream-tools = "0f60697843bba6f8d721b14da92b1652563ccb95"
SRCREV_FORMAT = "linux"

kernel_do_compile:append() {
	oe_runmake dtbs CC="${KERNEL_CC} $cc_extra " LD="${KERNEL_LD}" ${KERNEL_EXTRA_ARGS}
	oe_runmake -C ${S}/ti-upstream-tools LINUX=${S} DTC=${B}/scripts/dtc/dtc O=${B} CC="${KERNEL_CC} $cc_extra " LD="${KERNEL_LD}" ${KERNEL_EXTRA_ARGS}
}

do_shared_workdir:prepend() {
	cd ${B}
	echo >> Module.symvers
}

FILES:${KERNEL_PACKAGE_NAME}-devicetree += "/${KERNEL_IMAGEDEST}/*.itb"
