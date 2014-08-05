SECTION = "kernel"
DESCRIPTION = "Linux kernel for TI devices"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

inherit kernel

require recipes-kernel/linux/linux-dtb.inc
require recipes-kernel/linux/setup-defconfig.inc

# Look in the generic major.minor directory for files
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-3.14:"

# Pull in the devicetree files into the rootfs
RDEPENDS_kernel-base += "kernel-devicetree"

# Add a run-time dependency for the PM firmware to be installed
# on the target file system.
RDEPENDS_kernel-base_append_ti33x = " am33x-cm3"
RDEPENDS_kernel-base_append_ti43x = " am33x-cm3"

# Add a run-time dependency for the VPE VPDMA firmware to be installed
# on the target file system.
RDEPENDS_kernel-base_append_dra7xx = " vpe-vpdma-fw"

# Default is to package all dtb files for ti33x devices unless building
# for the specific beaglebone machine.
KERNEL_DEVICETREE_ti33x = "am335x-evm.dtb am335x-evmsk.dtb am335x-bone.dtb am335x-boneblack.dtb"
KERNEL_DEVICETREE_ti43x = "am43x-epos-evm.dtb am437x-gp-evm.dtb"
KERNEL_DEVICETREE_beaglebone = "am335x-bone.dtb am335x-boneblack.dtb"
KERNEL_DEVICETREE_omap5-evm = "omap5-uevm.dtb"
KERNEL_DEVICETREE_dra7xx = "dra7-evm.dtb"
KERNEL_DEVICETREE_omap3 = "omap3-beagle.dtb omap3-beagle-xm.dtb omap3-evm.dtb omap3-evm-37xx.dtb am3517-evm.dtb"
KERNEL_DEVICETREE_am3517-evm = "am3517-evm.dtb"
KERNEL_DEVICETREE_am37x-evm = "omap3-evm-37xx.dtb"
KERNEL_DEVICETREE_beagleboard = "omap3-beagle.dtb omap3-beagle-xm.dtb"

KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT}"

COMPATIBLE_MACHINE = "ti33x|ti43x|omap-a15|omap3"

S = "${WORKDIR}/git"

BRANCH = "ti-linux-3.14.y"

SRCREV = "780f0589876618911eb9cd7ad5a83db249ad1638"
PV = "3.14.15"

# Append to the MACHINE_KERNEL_PR so that a new SRCREV will cause a rebuild
MACHINE_KERNEL_PR_append = "a+gitr${SRCPV}"
PR = "${MACHINE_KERNEL_PR}"

KERNEL_CONFIG_DIR = "${S}/ti_config_fragments"
KERNEL_CONFIG_FRAGMENTS = "${KERNEL_CONFIG_DIR}/audio_display.cfg ${KERNEL_CONFIG_DIR}/baseport.cfg \
                           ${KERNEL_CONFIG_DIR}/connectivity.cfg ${KERNEL_CONFIG_DIR}/ipc.cfg \
                           ${KERNEL_CONFIG_DIR}/power.cfg"

KERNEL_CONFIG_FRAGMENTS_append_ti33x = " ${WORKDIR}/non-smp.cfg"
KERNEL_CONFIG_FRAGMENTS_append_ti43x = " ${WORKDIR}/non-smp.cfg"

# Patches necessary to make SGX graphics work with this kernel version
SGX_PATCHES = "file://sgx/0001-HACK-drm-fb_helper-enable-panning-support.patch \
               file://sgx/0002-HACK-drm-tilcdc-add-vsync-callback-for-use-in-omaplf.patch \
               file://sgx/0003-drm-tilcdc-fix-the-ping-pong-dma-tearing-issue-seen-.patch"

SRC_URI = "git://git.ti.com/ti-linux-kernel/ti-linux-kernel.git;protocol=git;branch=${BRANCH} \
           file://defconfig \
           file://non-smp.cfg \
          "

SRC_URI_append_ti33x = " ${SGX_PATCHES}"
SRC_URI_append_ti43x = " ${SGX_PATCHES}"
