SECTION = "kernel"
DESCRIPTION = "Linux kernel for TI devices"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

inherit kernel

require recipes-kernel/linux/setup-defconfig.inc
require recipes-kernel/linux/cmem.inc
require recipes-kernel/linux/ti-uio.inc

# Look in the generic major.minor directory for files
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.14:"

# Pull in the devicetree files into the rootfs
RDEPENDS_${KERNEL_PACKAGE_NAME}-base += "kernel-devicetree"

# Add run-time dependency for PM firmware to the rootfs
RDEPENDS_${KERNEL_PACKAGE_NAME}-base_append_ti33x = " amx3-cm3"
RDEPENDS_${KERNEL_PACKAGE_NAME}-base_append_ti43x = " amx3-cm3"

# Add run-time dependency for VPE VPDMA firmware to the rootfs
RDEPENDS_${KERNEL_PACKAGE_NAME}-base_append_dra7xx = " vpdma-fw"

# Add run-time dependency for Goodix firmware to the rootfs
RDEPENDS_${KERNEL_PACKAGE_NAME}-base_append_dra7xx = " goodix-fw"

# Install boot-monitor skern file into /boot dir of rootfs
RDEPENDS_${KERNEL_PACKAGE_NAME}-base_append_keystone = " boot-monitor"

# Install ti-sci-fw into /boot dir of rootfs
RDEPENDS_${KERNEL_PACKAGE_NAME}-base_append_k2g = " ti-sci-fw"

# Add run-time dependency for SerDes firmware to the rootfs
RDEPENDS_${KERNEL_PACKAGE_NAME}-base_append_keystone = " serdes-fw"

# Add run-time dependency for QMSS PDSP firmware to the rootfs
RDEPENDS_${KERNEL_PACKAGE_NAME}-base_append_keystone = " qmss-pdsp-fw"

# Add run-time dependency for NETCP PA firmware to the rootfs
RDEPENDS_${KERNEL_PACKAGE_NAME}-base_append_k2hk = " netcp-pa-fw"
RDEPENDS_${KERNEL_PACKAGE_NAME}-base_append_k2e = " netcp-pa-fw"
RDEPENDS_${KERNEL_PACKAGE_NAME}-base_append_k2l = " netcp-pa-fw"

# Add run-time dependency for PRU Ethernet firmware to the rootfs
RDEPENDS_${KERNEL_PACKAGE_NAME}-base_append_am57xx-evm = " prueth-fw"
RDEPENDS_${KERNEL_PACKAGE_NAME}-base_append_am437x-evm = " prueth-fw"
RDEPENDS_${KERNEL_PACKAGE_NAME}-base_append_am335x-evm = " prueth-fw"
RDEPENDS_${KERNEL_PACKAGE_NAME}-base_append_k2g = " prueth-fw"
RDEPENDS_${KERNEL_PACKAGE_NAME}-base_append_am65xx-evm = " prueth-fw-bin"

KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT}"

S = "${WORKDIR}/git"

BRANCH = "ti-lsk-linux-4.14.y"

SRCREV = "0b59bc3be7f2b7ba62fcbd38a5c162c7c772edbc"
PV = "4.14.54+git${SRCPV}"

# Append to the MACHINE_KERNEL_PR so that a new SRCREV will cause a rebuild
MACHINE_KERNEL_PR_append = "b"
PR = "${MACHINE_KERNEL_PR}"

KERNEL_GIT_URI = "git://git.ti.com/ti-linux-kernel/ti-linux-kernel.git"
KERNEL_GIT_PROTOCOL = "git"
SRC_URI += "${KERNEL_GIT_URI};protocol=${KERNEL_GIT_PROTOCOL};branch=${BRANCH} \
            file://defconfig"

FILES_${KERNEL_PACKAGE_NAME}-devicetree += "/${KERNEL_IMAGEDEST}/*.itb"
