# Support multiple BSP providers
# The main input is TI_PREFERRED_BSP, which translates into default
# preferences for kernel, bootloader and graphics components, as
# well as a dedicated machine override.

# Supported options are: mainline, ti-6_6, ti-6_1, bb_org
TI_PREFERRED_BSP ??= "ti-6_6"

# Use bsp-* namespace for overrides
MACHINEOVERRIDES =. "bsp-${TI_PREFERRED_BSP}:"

# ==========
# mainline
# latest upstream/mainline kernel, u-boot
# ==========
BSP_KERNEL_PROVIDER:bsp-mainline = "linux-ti-mainline"
BSP_KERNEL_VERSION:bsp-mainline = "%"
BSP_BOOTLOADER_PROVIDER:bsp-mainline = "u-boot-ti-mainline"
BSP_BOOTLOADER_VERSION:bsp-mainline = "%"

# GPU support requires out-of-tree SGX and Rogue drivers not available
# in mainline, usually present in TI staging or derivative like BB.org
MACHINE_FEATURES:remove:bsp-mainline = "gpu"

# ==========
# ti-6_6
# TI staging kernel 6.6, u-boot 2024.04
# ==========
BSP_KERNEL_PROVIDER:bsp-ti-6_6 = "linux-ti-staging"
BSP_KERNEL_VERSION:bsp-ti-6_6 = "6.6%"
BSP_BOOTLOADER_PROVIDER:bsp-ti-6_6 = "u-boot-ti-staging"
BSP_BOOTLOADER_VERSION:bsp-ti-6_6 = "2024%"

# Only Rogue is enabled so far, SGX falls back to SW rendering
BSP_ROGUE_DRIVER_PROVIDER:bsp-ti-6_6 = "ti-img-rogue-driver"
BSP_ROGUE_DRIVER_VERSION:bsp-ti-6_6 = "24%"
BSP_ROGUE_UMLIBS_VERSION:bsp-ti-6_6 = "24%"
BSP_MESA_PVR_VERSION:bsp-ti-6_6 = "23%"

# ==========
# ti-6_1
# TI staging kernel 6.1, u-boot 2023.04
# ==========
BSP_KERNEL_PROVIDER:bsp-ti-6_1 = "linux-ti-staging"
BSP_KERNEL_VERSION:bsp-ti-6_1 = "6.1%"
BSP_BOOTLOADER_PROVIDER:bsp-ti-6_1 = "u-boot-ti-staging"
BSP_BOOTLOADER_VERSION:bsp-ti-6_1 = "2023%"

BSP_SGX_DRIVER_PROVIDER:bsp-ti-6_1 = "ti-sgx-ddk-km"
BSP_SGX_DRIVER_VERSION:bsp-ti-6_1 = "1.17%"
BSP_SGX_UMLIBS_VERSION:bsp-ti-6_1 = "1.17%"
BSP_ROGUE_DRIVER_PROVIDER:bsp-ti-6_1 = "ti-img-rogue-driver"
BSP_ROGUE_DRIVER_VERSION:bsp-ti-6_1 = "23%"
BSP_ROGUE_UMLIBS_VERSION:bsp-ti-6_1 = "23%"
BSP_MESA_PVR_VERSION:bsp-ti-6_1 = "22%"

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

# ==========
# sane fallback defaults
# if specific values are not defined or bsp is set incorrectly
# use default preference TI staging and SW-rendering graphics
# ==========
BSP_KERNEL_PROVIDER ?= "linux-ti-staging"
BSP_KERNEL_VERSION ?= "%"
BSP_BOOTLOADER_PROVIDER ?= "u-boot-ti-staging"
BSP_BOOTLOADER_VERSION ?= "%"

BSP_SGX_DRIVER_PROVIDER ?= ""
BSP_SGX_DRIVER_VERSION ?= ""
BSP_ROGUE_DRIVER_PROVIDER ?= ""
BSP_ROGUE_DRIVER_VERSION ?= ""
BSP_SGX_UMLIBS_VERSION ?= ""
BSP_ROGUE_UMLIBS_VERSION ?= ""
BSP_MESA_PVR_VERSION ?= ""

# ==========
# global preferences
# ==========
PREFERRED_PROVIDER_virtual/kernel ?= "${BSP_KERNEL_PROVIDER}"
PREFERRED_VERSION_${BSP_KERNEL_PROVIDER} ?= "${BSP_KERNEL_VERSION}"
PREFERRED_PROVIDER_virtual/bootloader ?= "${BSP_BOOTLOADER_PROVIDER}"
PREFERRED_PROVIDER_u-boot ?= "${BSP_BOOTLOADER_PROVIDER}"
PREFERRED_VERSION_${BSP_BOOTLOADER_PROVIDER} ?= "${BSP_BOOTLOADER_VERSION}"

# GPU provider gets set in machine configs, as some machines are headless
# Select default preferred versions here
PREFERRED_VERSION_ti-sgx-ddk-km ?= "${BSP_SGX_DRIVER_VERSION}"
PREFERRED_VERSION_ti-sgx-ddk-um ?= "${BSP_SGX_UMLIBS_VERSION}"
PREFERRED_VERSION_ti-img-rogue-driver ?= "${BSP_ROGUE_DRIVER_VERSION}"
PREFERRED_VERSION_ti-img-rogue-umlibs ?= "${BSP_ROGUE_UMLIBS_VERSION}"
PREFERRED_VERSION_mesa-pvr ?= "${BSP_MESA_PVR_VERSION}"
