SUMMARY = "Minimal initramfs for boot requirements"

LICENSE = "MIT"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

TI_CORE_INITRAMFS_KERNEL_MODULES ?= ""

RDEPENDS:${PN} += "\
    ${TI_CORE_INITRAMFS_KERNEL_MODULES} \
    ${VIRTUAL-RUNTIME_base-utils} \
    ${VIRTUAL-RUNTIME_login_manager} \
    ${VIRTUAL-RUNTIME_init_manager} \
    ${VIRTUAL-RUNTIME_dev_manager} \
    ${VIRTUAL-RUNTIME_update-alternatives} \
    netbase \
    base-files \
    base-passwd \
    initramfs-framework-base \
    initramfs-module-udev \
    initramfs-module-nfsrootfs \
"
