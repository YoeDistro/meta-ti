# This is a generic TI SOC family. It is a superset of all other SOCs
# and platforms defined in meta-ti to allow BSP-level overrides.
SOC_FAMILY = "ti-soc"
require conf/machine/include/soc-family.inc
require conf/machine/include/ti-extras.inc

# kernel, initrd load addresses for the fitImage in all TI platforms
UBOOT_ENTRYPOINT = "0x82000000"
UBOOT_LOADADDRESS = "0x82000000"
UBOOT_RD_LOADADDRESS = "0x84000000"
UBOOT_RD_ENTRYPOINT = "0x84000000"

# TI platforms all use devicetrees with overlays
MACHINE_ESSENTIAL_EXTRA_RDEPENDS += "kernel-devicetree"

# Tell the kernel class to install the DTBs in the same directory structure as
# the kernel
KERNEL_DTBDEST = "${KERNEL_IMAGEDEST}/dtb"
KERNEL_DTBVENDORED = "1"

# Also build a non-vendored list w/o dir structure
DEVICETREE_FILES = "${@' '.join([os.path.basename(f) for f in d.getVar("KERNEL_DEVICETREE").split()])}"

KERNEL_DEVICETREE_DTBMERGE ?= ""

# Dynamically build DTBs list based on prefix matching
CLASS_DEVICETREE_PREFIX = "${@ 'ti-devicetree-prefix' if d.getVar('KERNEL_DEVICETREE_PREFIX') else ''}"

KERNEL_CLASSES += "${CLASS_DEVICETREE_PREFIX}"
IMAGE_CLASSES += "${CLASS_DEVICETREE_PREFIX}"

BUILD_CORE_INITRAMFS_IMAGE = "ti-core-initramfs"
BUILD_CORE_INITRAMFS_IMAGE_FILE = "${BUILD_CORE_INITRAMFS_IMAGE}.cpio.xz"

BUILD_CORE_INITRAMFS_IMAGE_STEP ?= ""
BUILD_CORE_INITRAMFS_IMAGE_STEP:bsp-mainline = "${BUILD_CORE_INITRAMFS_IMAGE}:do_image_complete"
BUILD_CORE_INITRAMFS_IMAGE_STEP:bsp-next = "${BUILD_CORE_INITRAMFS_IMAGE}:do_image_complete"

do_image_wic[depends] += "${BUILD_CORE_INITRAMFS_IMAGE_STEP}"

TI_WKS_INITRAMFS ?= ""
TI_WKS_INITRAMFS:bsp-mainline = ",initrd=${BUILD_CORE_INITRAMFS_IMAGE_FILE}"
TI_WKS_INITRAMFS:bsp-next = ",initrd=${BUILD_CORE_INITRAMFS_IMAGE_FILE}"

IMAGE_BOOT_FILES:append:bsp-mainline = " ${BUILD_CORE_INITRAMFS_IMAGE_FILE}"
IMAGE_BOOT_FILES:append:bsp-next = " ${BUILD_CORE_INITRAMFS_IMAGE_FILE}"
