SUMMARY = "TI SDK minimal initramfs image"

DESCRIPTION = "Image meant to probe boot essential modules\
 and other modules to reach the userspace, which cannot be\
 built inside the upstream linux kernel image.\
"

LICENSE = "MIT"

inherit core-image

IMAGE_NAME = "ti-core-initramfs"

IMAGE_NAME_SUFFIX = ""

IMAGE_FEATURES:remove = "package-management"

INITRAMFS_FSTYPES = "cpio cpio.xz"

IMAGE_FSTYPES = "${INITRAMFS_FSTYPES}"

PACKAGE_INSTALL = "packagegroup-ti-core-initramfs"

export IMAGE_BASENAME = "${IMAGE_NAME}"

IMAGE_OVERHEAD_FACTOR = "1.1"

# To further reduce the size of the rootfs, remove the /boot directory from
# the final image this is usually done by adding RDEPENDS_kernel-base = ""
# in the configuration file. In our case we can't use this method. Instead we
# just wipe out the content of "/boot" before creating the image.
ROOTFS_POSTPROCESS_COMMAND += "empty_boot_dir; "
empty_boot_dir () {
	rm -rf ${IMAGE_ROOTFS}/boot/*
}
