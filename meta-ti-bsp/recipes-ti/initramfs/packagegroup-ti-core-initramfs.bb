SUMMARY = "Minimal initramfs for boot requirements"

require recipes-core/packagegroups/packagegroup-core-boot.bb

TI_INITRAMFS_KERNEL_MODULES ?= ""

RDEPENDS:${PN} += "\
    ${TI_INITRAMFS_KERNEL_MODULES} \
    initramfs-framework-base \
    initramfs-module-udev \
    initramfs-module-nfsrootfs \
    nfs-utils \
    nfs-utils-client \
"

RDEPENDS:${PN}:remove = "grub-efi kernel"
