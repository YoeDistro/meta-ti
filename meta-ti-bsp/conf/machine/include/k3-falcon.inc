# TI K3 Aarch64 profile for Cortex-A53/A72 cores

require conf/machine/include/ti-soc.inc
SOC_FAMILY:append = ":k3-falcon"

DEFAULTTUNE ?= "aarch64"
require conf/machine/include/arm/armv8a/tune-cortexa72-cortexa53.inc

require conf/machine/include/ti-bsp.inc

KERNEL_IMAGETYPE = "Image"
KERNEL_CLASSES += "kernel-fit-extra-artifacts"

UBOOT_ARCH = "arm"
UBOOT_DTB_LOADADDRESS = "0x88000000"
UBOOT_DTBO_LOADADDRESS = "0x88080000"
UBOOT_DTBO_OFFSET = "0x00010000"

SPL_BINARY = "tispl.bin"
SPL_BINARYNAME = "tispl.bin"
UBOOT_SUFFIX = "img"

FIT_KERNEL_SIGN_ENABLE = "1"
FIT_KERNEL_SIGN_KEYNAME ?= "custMpk"
FIT_KERNEL_SIGN_KEYDIR ?= "${TI_SECURE_DEV_PKG}/keys"
UBOOT_MKIMAGE_DTCOPTS = "-I dts -O dtb"
FIT_HASH_ALG ?= "sha512"
FIT_SIGN_ALG ?= "rsa4096"

TFA_PLATFORM = "k3"

# Use the expected value of the ubifs filesystem's volume name in the kernel
# and u-boot.
UBI_VOLNAME = "rootfs"

SERIAL_CONSOLES = "115200;ttyS0 115200;ttyS2"

