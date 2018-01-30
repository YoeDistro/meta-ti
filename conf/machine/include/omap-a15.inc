require conf/machine/include/ti-soc.inc
SOC_FAMILY_append = ":omap-a15"

DEFAULTTUNE ?= "armv7athf-neon"
require conf/machine/include/tune-cortexa15.inc

# Increase this everytime you change something in the kernel
MACHINE_KERNEL_PR = "r7"

PREFERRED_PROVIDER_virtual/kernel = "linux-ti-staging"
PREFERRED_PROVIDER_virtual/bootloader = "u-boot-ti-staging"
PREFERRED_PROVIDER_u-boot = "u-boot-ti-staging"

KERNEL_IMAGETYPE = "zImage"
KERNEL_IMAGETYPES = "zImage uImage"
KERNEL_CLASSES += "kernel-uimage"

UBOOT_ARCH = "arm"
UBOOT_ENTRYPOINT = "0x80008000"
UBOOT_LOADADDRESS = "0x80008000"

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
MACHINE_FEATURES = "kernel26 apm usbgadget usbhost vfat ext2 screen alsa ethernet sgx mmip dsp"
