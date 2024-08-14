require conf/machine/include/k3.inc
SOC_FAMILY:append = ":j721e"

MACHINE_FEATURES += "screen gpu"

require conf/machine/include/mesa-pvr.inc
PREFERRED_PROVIDER_virtual/gpudriver ?= "${BSP_ROGUE_DRIVER_PROVIDER}"

TI_BOOTLOADERS += "sysfw.itb"

# Default on J721e is for GP
TI_BOOTLOADERS += "tiboot3-j721e-gp-evm.bin"
TI_BOOTLOADERS += "sysfw-j721e-gp-evm.itb"
TI_MULTICONFIGS += "j721e-gp-evm"

# Since default on J721e is for GP, add a version for SR1.1 HS-FS
IMAGE_SR1_1_HS_FS_FILES = "tiboot3-j721e_sr1_1-hs-fs-evm.bin sysfw-j721e_sr1_1-hs-fs-evm.itb"
IMAGE_SR1_1_HS_FS_FILES:bsp-mainline = ""
MULTICONFIG_SR1_1_HS_FS = "j721e_sr1_1-hs-fs-evm"
MULTICONFIG_SR1_1_HS_FS:bsp-mainline = ""
TI_BOOTLOADERS += "${IMAGE_SR1_1_HS_FS_FILES}"
TI_MULTICONFIGS += "${MULTICONFIG_SR1_1_HS_FS}"

# Since default on J721e is for GP, add a version for SR1.1 HS-SE
IMAGE_SR1_1_HS_SE_FILES = "tiboot3-j721e_sr1_1-hs-evm.bin sysfw-j721e_sr1_1-hs-evm.itb"
TI_BOOTLOADERS += "${IMAGE_SR1_1_HS_SE_FILES}"
TI_MULTICONFIGS += "j721e_sr1_1-hs-evm"

# Since default on J721e is for GP, add a version for SR2.0 HS-FS
IMAGE_SR2_HS_FS_FILES = "tiboot3-j721e_sr2-hs-fs-evm.bin sysfw-j721e_sr2-hs-fs-evm.itb"
TI_BOOTLOADERS += "${IMAGE_SR2_HS_FS_FILES}"
TI_MULTICONFIGS += "j721e_sr2-hs-fs-evm"

# Since default on J721e is for GP, add a version for SR2.0 HS-SE
IMAGE_SR2_HS_SE_FILES = "tiboot3-j721e_sr2-hs-evm.bin sysfw-j721e_sr2-hs-evm.itb"
IMAGE_SR2_HS_SE_FILES:bsp-mainline = ""
MULTICONFIG_SR2_HS_SE = "j721e_sr2-hs-evm"
MULTICONFIG_SR2_HS_SE:bsp-mainline = ""
TI_BOOTLOADERS += "${IMAGE_SR2_HS_SE_FILES}"
TI_MULTICONFIGS += "${MULTICONFIG_SR2_HS_SE}"

TFA_BOARD = "generic"

OPTEEMACHINE = "k3-j721e"

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS += "cadence-mhdp-fw vxd-dec-fw ti-eth-fw"
