require conf/machine/include/ti-soc.inc
SOC_FAMILY_append = ":omap3"

DEFAULTTUNE ?= "armv7athf-neon"
require conf/machine/include/tune-cortexa8.inc

PREFERRED_PROVIDER_virtual/xserver = "xserver-xorg"

# For built-in LCD, add xf86-input-tslib
XSERVER = "xserver-xorg \
           xf86-input-evdev \
           xf86-input-mouse \
           xf86-video-fbdev \
           xf86-input-keyboard"

# Default to external video, change to smallscreen for built-in LCD
MACHINE_GUI_CLASS = "bigscreen"

# Increase this everytime you change something in the kernel
MACHINE_KERNEL_PR = "r127"

KERNEL_IMAGETYPE = "zImage"

# Default providers, may need to override for specific machines
PREFERRED_PROVIDER_virtual/kernel = "linux-ti-staging"
PREFERRED_PROVIDER_virtual/bootloader = "u-boot-ti-staging"
PREFERRED_PROVIDER_u-boot = "u-boot-ti-staging"

KERNEL_DEVICETREE = "omap3-beagle.dtb omap3-beagle-xm.dtb omap3-beagle-xm-ab.dtb omap3-evm.dtb omap3-evm-37xx.dtb am3517-evm.dtb"

UBOOT_ARCH = "arm"
UBOOT_MACHINE = "omap3_evm_config"
XLOAD_MACHINE = "omap3evm_config"

UBOOT_ENTRYPOINT = "0x80008000"
UBOOT_LOADADDRESS = "0x80008000"

# If SPL is not used, may need to add x-load
EXTRA_IMAGEDEPENDS += "u-boot"

# List common SoC features, may need to add touchscreen/ethernet for specific machines
MACHINE_FEATURES = "kernel26 apm usbgadget usbhost vfat ext2 screen alsa sgx"
