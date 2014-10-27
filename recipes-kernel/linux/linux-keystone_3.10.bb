SECTION = "kernel"
DESCRIPTION = "Linux kernel for TI Keystone devices"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

COMPATIBLE_MACHINE = "keystone"

inherit kernel

require recipes-kernel/linux/linux-dtb.inc
require recipes-kernel/linux/setup-defconfig.inc

MACHINE_KERNEL_PR_append = "c+gitr${SRCPV}"
PR = "${MACHINE_KERNEL_PR}"

KERNEL_DEVICETREE_k2hk-evm = "k2hk-evm.dtb"
KERNEL_DEVICETREE_k2e-evm = "k2e-evm.dtb"
KERNEL_DEVICETREE_k2l-evm = "k2l-evm.dtb"

KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT}"

#This commit corresponds to "K2_LINUX_03.10.10_14.09_01"
SRCREV = "a59abdfadb821f27cc43b16d3f641960ba143746"
PV = "3.10.10"

BRANCH = "master"
SRC_URI = "git://git.ti.com/keystone-linux/linux.git;protocol=git;branch=${BRANCH}\
           file://defconfig\
           "

S = "${WORKDIR}/git"

RDEPENDS_kernel-base = ""
