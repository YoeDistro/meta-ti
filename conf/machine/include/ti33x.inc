require conf/machine/include/ti-soc.inc
SOC_FAMILY_append = ":ti33x"

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
MACHINE_KERNEL_PR = "r22"

# Default providers, may need to override for specific machines
PREFERRED_PROVIDER_virtual/kernel = "linux-ti-staging"
PREFERRED_PROVIDER_virtual/bootloader = "u-boot-ti-staging"
PREFERRED_PROVIDER_u-boot = "u-boot-ti-staging"

KERNEL_IMAGETYPE = "zImage"

KERNEL_DEVICETREE = "am335x-evm.dtb am335x-evmsk.dtb am335x-icev2.dtb \
                    am335x-bone.dtb am335x-boneblue.dtb \
                    am335x-boneblack.dtb am335x-boneblack-wireless.dtb \
                    am335x-bonegreen.dtb am335x-bonegreen-wireless.dtb"

KERNEL_DEVICETREE += "${@oe.utils.conditional('ENABLE_TI_UIO_DEVICES', '1', 'am335x-icev2-pru-excl-uio.dtb', '', d)}"

UBOOT_ARCH = "arm"
UBOOT_MACHINE = "am335x_evm_config"

UBOOT_ENTRYPOINT = "0x80008000"
UBOOT_LOADADDRESS = "0x80008000"

# Use the expected value of the ubifs filesystem's volume name in the kernel
# and u-boot.
UBI_VOLNAME = "rootfs"

EXTRA_IMAGEDEPENDS += "u-boot"

# List common SoC features, may need to add touchscreen for specific machines
MACHINE_FEATURES = "kernel26 apm usbgadget usbhost vfat ext2 screen alsa ethernet sgx"

# Set the list of device targets for ti-pdk class recipes
TI_PDK_LIMIT_SOCS = "am335x"
TI_PDK_LIMIT_BOARDS = "evmAM335x icev2AM335x iceAMIC110 bbbAM335x skAM335x"
