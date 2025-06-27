# Use meta-ti-bsp "multi-BSP" facility to create a custom "bb_org" BSP profile
# with default preferences for Beagle platforms

# Default is bb_org-6_12, but can override with any of the existing options
TI_PREFERRED_BSP ?= "bb_org-6_12"

# ===========
# bb_org-6_12
# BeagleBoard.org kernel 6.12, u-boot 2025.04
# based on TI staging trees with extra Beagle-specific features
# but may not be up-to-date with TI version
# ===========
BSP_KERNEL_PROVIDER:bsp-bb_org-6_12 = "linux-bb.org"
BSP_KERNEL_VERSION:bsp-bb_org-6_12 = "6.12.%"
BSP_BOOTLOADER_PROVIDER:bsp-bb_org-6_12 = "u-boot-bb.org"
BSP_BOOTLOADER_VERSION:bsp-bb_org-6_12 = "2025.%"

BSP_SGX_DRIVER_PROVIDER:bsp-bb_org-6_12 = "ti-sgx-ddk-km"
BSP_SGX_DRIVER_VERSION:bsp-bb_org-6_12 = "1.17%"
BSP_SGX_UMLIBS_VERSION:bsp-bb_org-6_12 = "1.17%"
BSP_ROGUE_DRIVER_PROVIDER:bsp-bb_org-6_12 = "ti-img-rogue-driver"
BSP_ROGUE_DRIVER_VERSION:bsp-bb_org-6_12 = "24%"
BSP_ROGUE_UMLIBS_VERSION:bsp-bb_org-6_12 = "24%"
BSP_MESA_PVR_VERSION:bsp-bb_org-6_12 = "24%"

# GC320 support requires out-of-tree drivers not yet available in bb.org 6.12
MACHINE_FEATURES:remove:bsp-bb_org-6_12 = "gc320"

# ==========
# bb_org-6_6
# BeagleBoard.org kernel 6.6, u-boot 2024.10
# based on TI staging trees with extra Beagle-specific features
# but may not be up-to-date with TI version
# ==========
BSP_KERNEL_PROVIDER:bsp-bb_org-6_6 = "linux-bb.org"
BSP_KERNEL_VERSION:bsp-bb_org-6_6 = "6.6.%"
BSP_BOOTLOADER_PROVIDER:bsp-bb_org-6_6 = "u-boot-bb.org"
BSP_BOOTLOADER_VERSION:bsp-bb_org-6_6 = "2024.%"

BSP_SGX_DRIVER_PROVIDER:bsp-bb_org-6_6 = "ti-sgx-ddk-km"
BSP_SGX_DRIVER_VERSION:bsp-bb_org-6_6 = "1.17%"
BSP_SGX_UMLIBS_VERSION:bsp-bb_org-6_6 = "1.17%"
BSP_ROGUE_DRIVER_PROVIDER:bsp-bb_org-6_6 = "ti-img-rogue-driver"
BSP_ROGUE_DRIVER_VERSION:bsp-bb_org-6_6 = "24%"
BSP_ROGUE_UMLIBS_VERSION:bsp-bb_org-6_6 = "24%"
BSP_MESA_PVR_VERSION:bsp-bb_org-6_6 = "24%"

# GC320 support requires out-of-tree drivers not yet available in bb.org 6.6
MACHINE_FEATURES:remove:bsp-bb_org-6_6 = "gc320"
