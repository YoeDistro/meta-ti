require conf/machine/include/ti-soc.inc
SOC_FAMILY:append = ":omap-a15"

DEFAULTTUNE ?= "armv7athf-neon"
require conf/machine/include/arm/armv7a/tune-cortexa15.inc

require conf/machine/include/ti-bsp.inc

# Graphics providers and variables
require conf/machine/include/mesa-pvr.inc
PREFERRED_PROVIDER_virtual/gpudriver ?= "${BSP_SGX_DRIVER_PROVIDER}"
PVR_DISPLAY_CONTROLLER_ALIAS ?= "omapdrm"

KERNEL_IMAGETYPE = "zImage"
KERNEL_IMAGETYPES = "zImage uImage"
KERNEL_CLASSES += "kernel-uimage"

UBOOT_ARCH = "arm"
UBOOT_ENTRYPOINT = "0x80008000"
UBOOT_LOADADDRESS = "0x80008000"

DEVICETREE_FILE = "${@(d.getVar('KERNEL_DEVICETREE') or "").replace("ti/omap/","").replace("ti/","")}"

# Generate an extlinux.conf file
UBOOT_EXTLINUX = "1"
UBOOT_EXTLINUX_ROOT = "root=PARTUUID=${uuid}"
UBOOT_EXTLINUX_BOOT_FILES = " \
    extlinux.conf;extlinux/extlinux.conf \
    ${KERNEL_IMAGETYPE} \
    ${DEVICETREE_FILE} \
"

SPL_BINARY = "MLO"
UBOOT_SUFFIX = "img"

EXTRA_IMAGEDEPENDS += "virtual/bootloader"

MACHINE_ESSENTIAL_EXTRA_RDEPENDS += "kernel-image-zimage"

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS += "vpdma-fw goodix-fw"

# Use the expected value of the ubifs filesystem's volume name in the kernel
# and u-boot.
UBI_VOLNAME = "rootfs"

# List common SoC features, may need to add touchscreen for specific machines
MACHINE_FEATURES = "apm usbgadget usbhost vfat ext2 screen alsa gpu mmip dsp gc320"

IMAGE_FSTYPES += "tar.xz wic.xz wic.bmap"
WKS_FILE ?= "sdimage-2part.wks"
IMAGE_BOOT_FILES ?= "${SPL_BINARY} u-boot.${UBOOT_SUFFIX} ${UBOOT_EXTLINUX_BOOT_FILES} ipc/dra7-ipu1-fw.xem4;dra7-ipu1-fw.xem4"
do_image_wic[depends] += "virtual/bootloader:do_deploy"
do_image_wic[depends] += "ti-ipc-rtos:do_deploy"
