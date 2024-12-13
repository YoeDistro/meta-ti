# Use meta-ti-bsp "multi-BSP" facility to create a custom "bb_org" BSP profile
# with default preferences for Beagle platforms

# Default is bb_org, but can override with any of the existing options
TI_PREFERRED_BSP ?= "bb_org"

# ==========
# bb_org
# BeagleBoard.org kernel 6.1, u-boot 2023.04
# based on TI staging trees with extra Beagle-specific features
# but may not be up-to-date with TI version
# ==========
BSP_KERNEL_PROVIDER:bsp-bb_org = "linux-bb.org"
BSP_KERNEL_VERSION:bsp-bb_org = "%"
BSP_BOOTLOADER_PROVIDER:bsp-bb_org = "u-boot-bb.org"
BSP_BOOTLOADER_VERSION:bsp-bb_org = "%"

BSP_SGX_DRIVER_PROVIDER:bsp-bb_org = "ti-sgx-ddk-km"
BSP_SGX_DRIVER_VERSION:bsp-bb_org = "1.17%"
BSP_SGX_UMLIBS_VERSION:bsp-bb_org = "1.17%"
BSP_ROGUE_DRIVER_PROVIDER:bsp-bb_org = "ti-img-rogue-driver"
BSP_ROGUE_DRIVER_VERSION:bsp-bb_org = "23%"
BSP_ROGUE_UMLIBS_VERSION:bsp-bb_org = "23%"
BSP_MESA_PVR_VERSION:bsp-bb_org = "22%"
