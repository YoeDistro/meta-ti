require conf/machine/include/k3.inc
require conf/machine/include/mc_k3r5.inc

SOC_FAMILY:append = ":j7200"

# Default tiboot3.bin on J7200 is for GP
IMAGE_BOOT_FILES += "tiboot3-j7200-gp-evm.bin"

# Since default tiboot3.bin on J7200 is for GP, add a version for SR1.0 HS-FS
IMAGE_SR1_HS_FS_FILES = "tiboot3-j7200-hs-fs-evm.bin"
IMAGE_SR1_HS_FS_FILES:bsp-mainline = ""
IMAGE_BOOT_FILES += "${IMAGE_SR1_HS_FS_FILES}"

# Since default tiboot3.bin on J7200 is for GP, add a version for SR1.0 HS-SE
IMAGE_SR1_HS_SE_FILES = "tiboot3-j7200-hs-evm.bin"
IMAGE_SR1_HS_SE_FILES:bsp-mainline = ""
IMAGE_BOOT_FILES += "${IMAGE_SR1_HS_SE_FILES}"

# Since default tiboot3.bin on J7200 is for GP, add a version for SR2.0 HS-FS
IMAGE_BOOT_FILES += "tiboot3-j7200_sr2-hs-fs-evm.bin"

# Since default tiboot3.bin on J721e is for GP, add a version for SR2.0 HS-SE
IMAGE_BOOT_FILES += "tiboot3-j7200_sr2-hs-evm.bin"

TFA_BOARD = "generic"

OPTEEMACHINE = "k3-j721e"

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS += "ti-eth-fw"
