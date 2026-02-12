SUMMARY = "TI SDK minimal initramfs image"

DESCRIPTION = "Image meant to probe boot essential modules\
 and other modules to reach the userspace, which cannot be\
 built inside the upstream linux kernel image.\
"

LICENSE = "MIT"

INITRAMFS_FSTYPES = "cpio cpio.xz"

INITRAMFS_MAXSIZE = "131072"

IMAGE_NAME = "ti-core-initramfs"

export IMAGE_BASENAME = "${IMAGE_NAME}"

TI_CORE_INITRAMFS_EXTRA_INSTALL ?= ""

PACKAGE_INSTALL = "\
    packagegroup-ti-core-initramfs \
    ${TI_CORE_INITRAMFS_EXTRA_INSTALL} \
"

# Ensure the initramfs only contains the bare minimum
IMAGE_FEATURES = ""
IMAGE_LINGUAS = ""

# on the kernel image.
PACKAGE_EXCLUDE = "kernel-image-*"

IMAGE_FSTYPES = "${INITRAMFS_FSTYPES}"
IMAGE_NAME_SUFFIX ?= ""
IMAGE_ROOTFS_SIZE = "8192"
IMAGE_ROOTFS_EXTRA_SPACE = "0"

inherit image
