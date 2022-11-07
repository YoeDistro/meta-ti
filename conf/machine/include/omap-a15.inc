require conf/machine/include/ti-soc.inc
SOC_FAMILY_append = ":omap-a15"

DEFAULTTUNE ?= "armv7athf-neon"
require conf/machine/include/tune-cortexa15.inc

# Increase this everytime you change something in the kernel
MACHINE_KERNEL_PR = "r7"

PREFERRED_PROVIDER_virtual/kernel ?= "linux-ti-staging"
PREFERRED_PROVIDER_virtual/bootloader = "u-boot-ti-staging"
PREFERRED_PROVIDER_u-boot = "u-boot-ti-staging"

KERNEL_IMAGETYPE = "zImage"
KERNEL_IMAGETYPES = "zImage uImage"
KERNEL_CLASSES += "kernel-uimage"

UBOOT_ARCH = "arm"
UBOOT_ENTRYPOINT = "0x80008000"
UBOOT_LOADADDRESS = "0x80008000"

DEVICETREE_FILE = "${@(d.getVar('KERNEL_DEVICETREE') or "").replace("ti/","")}"

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

PREFERRED_PROVIDER_virtual/xserver = "xserver-xorg"
XSERVER = "xserver-xorg \
           xf86-input-evdev \
           xf86-video-fbdev"

MACHINE_GUI_CLASS = "smallscreen"

# Use the expected value of the ubifs filesystem's volume name in the kernel
# and u-boot.
UBI_VOLNAME = "rootfs"

# List common SoC features, may need to add touchscreen for specific machines
MACHINE_FEATURES = "kernel26 apm usbgadget usbhost vfat ext2 screen alsa ethernet gpu mmip dsp gc320"

IMAGE_FSTYPES += "tar.xz wic.xz wic.bmap"
WKS_FILE ?= "sdimage-2part.wks"
IMAGE_BOOT_FILES ?= "${SPL_BINARY} u-boot.${UBOOT_SUFFIX} ${UBOOT_EXTLINUX_BOOT_FILES}"
do_image_wic[depends] += "u-boot:do_deploy"
