require conf/machine/include/k3.inc
SOC_FAMILY:append = ":j7200"

# Default tiboot3.bin on J7200 is for GP
IMAGE_BOOT_FILES += "tiboot3-j7200-gp-evm.bin"

# Since default tiboot3.bin on J7200 is for GP, add a version for SR1.0 HS-FS
IMAGE_SR1_HS_FS_FILES = "tiboot3-j7200-hs-fs-evm.bin"
IMAGE_SR1_HS_FS_FILES:bsp-mainline = ""
MULTICONFIG_SR1_HS_FS = "j7200-hs-fs-evm"
MULTICONFIG_SR1_HS_FS:bsp-mainline = ""
TI_BOOTLOADERS += "${IMAGE_SR1_HS_FS_FILES}"
TI_MULTICONFIGS += "${MULTICONFIG_SR1_HS_FS}"

# Since default tiboot3.bin on J7200 is for GP, add a version for SR1.0 HS-SE
IMAGE_SR1_HS_SE_FILES = "tiboot3-j7200-hs-evm.bin"
IMAGE_SR1_HS_SE_FILES:bsp-mainline = ""
MULTICONFIG_SR1_HS_SE = "j7200-hs-evm"
MULTICONFIG_SR1_HS_SE:bsp-mainline = ""
TI_BOOTLOADERS += "${IMAGE_SR1_HS_SE_FILES}"
TI_MULTICONFIGS += "${MULTICONFIG_SR1_HS_SE}"

# Since default tiboot3.bin on J7200 is for GP, add a version for SR2.0 HS-FS
TI_BOOTLOADERS += "tiboot3-j7200_sr2-hs-fs-evm.bin"
TI_MULTICONFIGS += "j7200_sr2-hs-fs-evm"

# Since default tiboot3.bin on J721e is for GP, add a version for SR2.0 HS-SE
TI_BOOTLOADERS += "tiboot3-j7200_sr2-hs-evm.bin"
TI_MULTICONFIGS += "j7200_sr2-hs-evm"

TFA_BOARD = "generic"

OPTEEMACHINE = "k3-j721e"

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS += "ti-eth-fw"
