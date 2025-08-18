require conf/machine/include/k3.inc
require conf/machine/include/mc_k3r5.inc

SOC_FAMILY:append = ":j721e"

MACHINE_FEATURES += "screen gpu"

require conf/machine/include/mesa-pvr.inc
PREFERRED_PROVIDER_virtual/gpudriver ?= "${BSP_ROGUE_DRIVER_PROVIDER}"

# Deploy SYSFW and any variants
IMAGE_BOOT_FILES += "sysfw.itb sysfw-*-evm.itb"

TFA_BOARD = "generic"

OPTEEMACHINE = "k3-j721e"

TI_VXE_VXD_DRIVER = "ti-vxe-vxd-driver"
TI_VXE_VXD_DRIVER:bsp-ti-6_6 = ""

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS += "cadence-mhdp-fw vxd-dec-fw ti-eth-fw-j721e ${TI_VXE_VXD_DRIVER}"
