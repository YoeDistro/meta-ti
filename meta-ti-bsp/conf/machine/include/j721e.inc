require conf/machine/include/k3.inc
require conf/machine/include/mc_k3r5.inc

SOC_FAMILY:append = ":j721e"

MACHINE_FEATURES += "screen gpu"

require conf/machine/include/mesa-pvr.inc
PREFERRED_PROVIDER_virtual/gpudriver ?= "${BSP_ROGUE_DRIVER_PROVIDER}"

IMAGE_BOOT_FILES += "sysfw.itb"

# Default on J721e is for GP
IMAGE_BOOT_FILES += "tiboot3-j721e-gp-evm.bin"
IMAGE_BOOT_FILES += "sysfw-j721e-gp-evm.itb"

# Since default on J721e is for GP, add a version for SR1.1 HS-FS
IMAGE_SR1_1_HS_FS_FILES = "tiboot3-j721e_sr1_1-hs-fs-evm.bin sysfw-j721e_sr1_1-hs-fs-evm.itb"
IMAGE_SR1_1_HS_FS_FILES:bsp-mainline = ""
IMAGE_BOOT_FILES += "${IMAGE_SR1_1_HS_FS_FILES}"

# Since default on J721e is for GP, add a version for SR1.1 HS-SE
IMAGE_SR1_1_HS_SE_FILES = "tiboot3-j721e_sr1_1-hs-evm.bin sysfw-j721e_sr1_1-hs-evm.itb"
IMAGE_BOOT_FILES += "${IMAGE_SR1_1_HS_SE_FILES}"

# Since default on J721e is for GP, add a version for SR2.0 HS-FS
IMAGE_SR2_HS_FS_FILES = "tiboot3-j721e_sr2-hs-fs-evm.bin sysfw-j721e_sr2-hs-fs-evm.itb"
IMAGE_BOOT_FILES += "${IMAGE_SR2_HS_FS_FILES}"

# Since default on J721e is for GP, add a version for SR2.0 HS-SE
IMAGE_SR2_HS_SE_FILES = "tiboot3-j721e_sr2-hs-evm.bin sysfw-j721e_sr2-hs-evm.itb"
IMAGE_SR2_HS_SE_FILES:bsp-mainline = ""
IMAGE_BOOT_FILES += "${IMAGE_SR2_HS_SE_FILES}"

TFA_BOARD = "generic"

OPTEEMACHINE = "k3-j721e"

TI_VXE_VXD_DRIVER = "ti-vxe-vxd-driver"
TI_VXE_VXD_DRIVER:bsp-ti-6_6 = ""

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS += "cadence-mhdp-fw vxd-dec-fw ti-eth-fw-j721e ${TI_VXE_VXD_DRIVER}"
