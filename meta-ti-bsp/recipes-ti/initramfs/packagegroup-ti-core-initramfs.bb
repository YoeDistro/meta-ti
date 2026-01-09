SUMMARY = "Minimal initramfs for boot requirements"

LICENSE = "MIT"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

TI_INITRAMFS_KERNEL_MODULES ?= ""

RDEPENDS:${PN} += "\
    ${TI_INITRAMFS_KERNEL_MODULES} \
    ${VIRTUAL-RUNTIME_base-utils} \
    base-passwd \
    initramfs-framework-base \
    initramfs-module-udev \
    initramfs-module-nfsrootfs \
"
