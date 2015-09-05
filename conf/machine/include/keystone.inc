SOC_FAMILY = "keystone"
require conf/machine/include/soc-family.inc

DEFAULTTUNE ?= "cortexa15thf-neon"
require conf/machine/include/tune-cortexa15.inc

PREFERRED_PROVIDER_virtual/kernel = "linux-ti-staging"
PREFERRED_PROVIDER_virtual/bootloader = "u-boot-ti-staging"
PREFERRED_PROVIDER_u-boot = "u-boot-ti-staging"

# Increase this everytime you change something in the kernel
MACHINE_KERNEL_PR = "r10"

KERNEL_IMAGETYPE = "zImage"

UBOOT_ENTRYPOINT = "0x80008000"
UBOOT_LOADADDRESS = "0x80008000"

# Use the expected value of the ubifs filesystem's volume name in the kernel
# and u-boot.
UBI_VOLNAME = "rootfs"

EXTRA_IMAGEDEPENDS += "u-boot"
EXTRA_IMAGEDEPENDS += "boot-monitor"

MACHINE_FEATURES = "kernel26 apm usbgadget usbhost vfat pci"
MACHINE_FEATURES_BACKFILL_CONSIDERED = "rtc"
