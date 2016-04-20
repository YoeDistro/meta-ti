SOC_FAMILY = "omap4"
require conf/machine/include/soc-family.inc

DEFAULTTUNE ?= "cortexa9thf-neon"
require conf/machine/include/tune-cortexa9.inc

PREFERRED_PROVIDER_virtual/kernel = "linux-ti-staging"
PREFERRED_PROVIDER_virtual/bootloader = "u-boot-ti-staging"
PREFERRED_PROVIDER_u-boot = "u-boot-ti-staging"

PREFERRED_PROVIDER_virtual/xserver = "xserver-xorg"
XSERVER = "xserver-xorg \
           xserver-xorg-module-exa \
           xf86-input-evdev \
           xf86-video-omap"

# Increase this everytime you change something in the kernel
MACHINE_KERNEL_PR = "r4"

KERNEL_IMAGETYPE = "zImage"

UBOOT_MACHINE = "omap4_panda_config"

UBOOT_ENTRYPOINT = "0x80008000"
UBOOT_LOADADDRESS = "0x80008000"

EXTRA_IMAGEDEPENDS += "u-boot"
