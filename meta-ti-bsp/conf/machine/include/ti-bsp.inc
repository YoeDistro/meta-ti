# Support multiple BSP providers
# The main input is TI_PREFERRED_BSP, which translates into default
# preferences for kernel, bootloader and graphics components, as
# well as a dedicated machine override.

# Supported options are: mainline, next, ti-6_18, ti-6_12, ti-6_6
TI_PREFERRED_BSP ??= "ti-6_12"

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

# GC320 support requires out-of-tree drivers not available in mainline
MACHINE_FEATURES:remove:bsp-mainline = "gc320"

# ==========
# next
# upcoming upstream/mainline kernel, u-boot
# ==========
BSP_KERNEL_PROVIDER:bsp-next = "linux-ti-next"
BSP_KERNEL_VERSION:bsp-next = "%"
BSP_BOOTLOADER_PROVIDER:bsp-next = "u-boot-ti-next"
BSP_BOOTLOADER_VERSION:bsp-next = "%"

# GC320 support requires out-of-tree drivers not yet available in next
MACHINE_FEATURES:remove:bsp-next = "gc320"

# ==========
# ti-6_18
# TI staging kernel 6.18, u-boot 2026.01
# ==========
BSP_KERNEL_PROVIDER:bsp-ti-6_18 = "linux-ti-staging"
BSP_KERNEL_VERSION:bsp-ti-6_18 = "6.18%"
BSP_BOOTLOADER_PROVIDER:bsp-ti-6_18 = "u-boot-ti-staging"
BSP_BOOTLOADER_VERSION:bsp-ti-6_18 = "2026.01%"

BSP_ROGUE_DRIVER_PROVIDER:bsp-ti-6_18 = "ti-img-rogue-driver"
BSP_ROGUE_DRIVER_VERSION:bsp-ti-6_18 = "25%"
BSP_MESA_PVR_VERSION:bsp-ti-6_18 = "24%"

# ==========
# ti-6_12
# TI staging kernel 6.12, u-boot 2025.01
# ==========
BSP_KERNEL_PROVIDER:bsp-ti-6_12 = "linux-ti-staging"
BSP_KERNEL_VERSION:bsp-ti-6_12 = "6.12%"
BSP_BOOTLOADER_PROVIDER:bsp-ti-6_12 = "u-boot-ti-staging"
BSP_BOOTLOADER_VERSION:bsp-ti-6_12 = "2025.01%"

BSP_SGX_DRIVER_PROVIDER:bsp-ti-6_12 = "ti-sgx-ddk-km"
BSP_SGX_DRIVER_VERSION:bsp-ti-6_12 = "1.17%"
BSP_ROGUE_DRIVER_PROVIDER:bsp-ti-6_12 = "ti-img-rogue-driver"
BSP_ROGUE_DRIVER_VERSION:bsp-ti-6_12 = "25%"
BSP_MESA_PVR_VERSION:bsp-ti-6_12 = "24%"

# ==========
# ti-6_6
# TI staging kernel 6.6, u-boot 2024.04
# ==========
BSP_KERNEL_PROVIDER:bsp-ti-6_6 = "linux-ti-staging"
BSP_KERNEL_VERSION:bsp-ti-6_6 = "6.6.%"
BSP_BOOTLOADER_PROVIDER:bsp-ti-6_6 = "u-boot-ti-staging"
BSP_BOOTLOADER_VERSION:bsp-ti-6_6 = "2024.04%"

BSP_SGX_DRIVER_PROVIDER:bsp-ti-6_6 = "ti-sgx-ddk-km"
BSP_SGX_DRIVER_VERSION:bsp-ti-6_6 = "1.17%"
BSP_ROGUE_DRIVER_PROVIDER:bsp-ti-6_6 = "ti-img-rogue-driver"
BSP_ROGUE_DRIVER_VERSION:bsp-ti-6_6 = "25%"
BSP_MESA_PVR_VERSION:bsp-ti-6_6 = "24%"

# GC320 support requires out-of-tree drivers not yet available in 6.6
MACHINE_FEATURES:remove:bsp-ti-6_6 = "gc320"

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
BSP_MESA_PVR_VERSION ?= ""

# ==========
# global preferences
# ==========
PREFERRED_PROVIDER_virtual/kernel ?= "${BSP_KERNEL_PROVIDER}"
PREFERRED_VERSION_${BSP_KERNEL_PROVIDER} ?= "${BSP_KERNEL_VERSION}"
PREFERRED_VERSION_${BSP_KERNEL_PROVIDER}-rt ?= "${BSP_KERNEL_VERSION}"
PREFERRED_PROVIDER_virtual/bootloader ?= "${BSP_BOOTLOADER_PROVIDER}"
PREFERRED_PROVIDER_u-boot ?= "${BSP_BOOTLOADER_PROVIDER}"
PREFERRED_VERSION_${BSP_BOOTLOADER_PROVIDER} ?= "${BSP_BOOTLOADER_VERSION}"

# GPU provider gets set in machine configs, as some machines are headless
# Select default preferred versions here
PREFERRED_VERSION_ti-sgx-ddk-km ?= "${BSP_SGX_DRIVER_VERSION}"
PREFERRED_VERSION_ti-sgx-ddk-um ?= "${BSP_SGX_DRIVER_VERSION}"
PREFERRED_VERSION_ti-img-rogue-driver ?= "${BSP_ROGUE_DRIVER_VERSION}"
PREFERRED_VERSION_ti-img-rogue-umlibs ?= "${BSP_ROGUE_DRIVER_VERSION}"
PREFERRED_VERSION_mesa-pvr ?= "${BSP_MESA_PVR_VERSION}"
