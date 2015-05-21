SECTION = "kernel"
DESCRIPTION = "Linux kernel for TI Keystone devices"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

COMPATIBLE_MACHINE = "keystone"

inherit kernel

require recipes-kernel/linux/linux-dtb.inc
require recipes-kernel/linux/setup-defconfig.inc

# Look in the generic major.minor directory for files
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-3.13:"

MACHINE_KERNEL_PR_append = "a"
PR = "${MACHINE_KERNEL_PR}"

# Only k2hk DTB is available in this version
KERNEL_DEVICETREE_k2hk-evm = "k2hk-evm.dtb"

KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT}"

SRCREV = "11a8eaee39b68e6047f07382c251786b40bef165"
PV = "3.13.0+git${SRCPV}"

BRANCH = "v3.13/master"
SRC_URI = "git://git.ti.com/keystone-linux/linux.git;protocol=git;branch=${BRANCH}\
           file://defconfig\
           "

S = "${WORKDIR}/git"

RDEPENDS_kernel-base = ""
