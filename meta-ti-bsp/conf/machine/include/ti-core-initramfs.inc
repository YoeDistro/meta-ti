#------------------------------------------------------------------------------
#
# To turn off the ti-core-initramfs.cpio creation just set:
#
#   TI_CORE_INITRAMFS_ENABLED = "0"
#
#------------------------------------------------------------------------------
TI_CORE_INITRAMFS_ENABLED ?= "${@ '1' if d.getVar('TI_CORE_INITRAMFS_KERNEL_MODULES') or d.getVar('TI_CORE_INITRAMFS_EXTRA_INSTALL') else '0'}"

TI_CORE_INITRAMFS_KERNEL_MODULES ?= ""
TI_CORE_INITRAMFS_EXTRA_INSTALL ?= ""

TI_CORE_INITRAMFS_IMAGE = "ti-core-initramfs"
TI_CORE_INITRAMFS_IMAGE_FILE = "${TI_CORE_INITRAMFS_IMAGE}.cpio.xz"

#------------------------------------------------------------------------------
# Apply all of the needed changes to create/use the initramfs (if enabled).
#------------------------------------------------------------------------------
do_image_wic[depends] += "${@ '${TI_CORE_INITRAMFS_IMAGE}:do_image_complete' if d.getVar('TI_CORE_INITRAMFS_ENABLED') == "1" else ''}"

TI_WKS_INITRAMFS ?= "${@ ',initrd=${TI_CORE_INITRAMFS_IMAGE_FILE}' if d.getVar('TI_CORE_INITRAMFS_ENABLED') == "1" else ''}"

IMAGE_BOOT_FILES += "${@ '${TI_CORE_INITRAMFS_IMAGE_FILE}' if d.getVar('TI_CORE_INITRAMFS_ENABLED') == "1" else ''}"
