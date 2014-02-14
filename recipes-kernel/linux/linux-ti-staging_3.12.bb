SECTION = "kernel"
DESCRIPTION = "Linux kernel for TI devices"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

inherit kernel

require recipes-kernel/linux/linux-dtb.inc
require recipes-kernel/linux/setup-defconfig.inc

# Pull in the devicetree files into the rootfs
RDEPENDS_kernel-base += "kernel-devicetree"

# Add a run-time dependency for the PM firmware to be installed
# on the target file system.
RDEPENDS_kernel-base_append_ti33x = " am33x-cm3"
RDEPENDS_kernel-base_append_ti43x = " am33x-cm3"

# Add a run-time dependency for the VPE VPDMA firmware to be installed
# on the target file system.
RDEPENDS_kernel-base_append_dra7xx-evm = " vpe-vpdma-fw"

# Default is to package all dtb files for ti33x devices unless building
# for the specific beaglebone machine.
KERNEL_DEVICETREE_ti33x = "am335x-evm.dtb am335x-evmsk.dtb am335x-bone.dtb am335x-boneblack.dtb"
KERNEL_DEVICETREE_ti43x = "am43x-epos-evm.dtb am437x-gp-evm.dtb"
KERNEL_DEVICETREE_beaglebone = "am335x-bone.dtb am335x-boneblack.dtb"
KERNEL_DEVICETREE_omap5-evm = "omap5-uevm.dtb"
KERNEL_DEVICETREE_dra7xx-evm = "dra7-evm.dtb"

KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT}"

COMPATIBLE_MACHINE = "ti33x|ti43x|omap-a15"

S = "${WORKDIR}/git"

BRANCH = "ti-linux-3.12.y"

SRCREV = "9d5b470605a6e5df5c46444c2b2c108d21f9a24a"
PV = "3.12.10"

# Append to the MACHINE_KERNEL_PR so that a new SRCREV will cause a rebuild
MACHINE_KERNEL_PR_append = "e+gitr${SRCPV}"
PR = "${MACHINE_KERNEL_PR}"

SRC_URI = "git://git.ti.com/ti-linux-kernel/ti-linux-kernel.git;protocol=git;branch=${BRANCH} \
           file://defconfig \
          "

# Performance fixes
SRC_URI += "file://0001-ARM-config-omap-Sync-up-omap2plus-defconfig-with-sav.patch \
            file://0002-ARM-config-omap-Make-advanced-networking-features-as.patch \
            file://0003-ARM-config-omap-Disable-extra-debug-options.patch \
            file://0005-ARM-OMAP-Kill-warning-in-CPUIDLE-code-with-CONFIG_SM.patch \
            "
SRC_URI_append_ti33x = "file://0004-Not-for-merge-ARM-config-omap-Disable-SMP-for-AM335x.patch"
SRC_URI_append_ti43x = "file://0004-Not-for-merge-ARM-config-omap-Disable-SMP-for-AM335x.patch"

# LCD resume fixes
SRC_URI += "file://0001-ARM-OMAP2-am43xx-Use-omap4-prm-context-ops.patch \
            file://0002-ARM-OMAP2-omap_hwmod-Add-context-ops-to-am43xx-soc_ops.patch \
            file://0003-ARM-OMAP2-am43xx-Add-context-offset-for-dss-hwmod.patch \
            file://0004-ARM-OMAP2-omap_hwmod-Maintain-legacy-context-loss-count.patch \
            "
