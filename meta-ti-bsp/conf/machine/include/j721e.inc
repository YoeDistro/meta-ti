require conf/machine/include/k3.inc
SOC_FAMILY:append = ":j721e"

MACHINE_FEATURES += "screen gpu"

require conf/machine/include/mesa-pvr.inc
PREFERRED_PROVIDER_virtual/gpudriver ?= "ti-img-rogue-driver"

IMAGE_BOOT_FILES += "sysfw.itb"

# Default on J721e is for GP
IMAGE_BOOT_FILES += "tiboot3-j721e-gp-evm.bin"
IMAGE_BOOT_FILES += "sysfw-j721e-gp-evm.itb"

# Since default on J721e is for GP, add a version for SR1.1 HS-FS
IMAGE_BOOT_FILES += "tiboot3-j721e_sr1_1-hs-fs-evm.bin"
IMAGE_BOOT_FILES += "sysfw-j721e_sr1_1-hs-fs-evm.itb"

# Since default on J721e is for GP, add a version for SR1.1 HS-SE
IMAGE_BOOT_FILES += "tiboot3-j721e_sr1_1-hs-evm.bin"
IMAGE_BOOT_FILES += "sysfw-j721e_sr1_1-hs-evm.itb"

# Since default on J721e is for GP, add a version for SR2.0 HS-FS
IMAGE_BOOT_FILES += "tiboot3-j721e_sr2-hs-fs-evm.bin"
IMAGE_BOOT_FILES += "sysfw-j721e_sr2-hs-fs-evm.itb"

# Since default on J721e is for GP, add a version for SR2.0 HS-SE
IMAGE_BOOT_FILES += "tiboot3-j721e_sr2-hs-evm.bin"
IMAGE_BOOT_FILES += "sysfw-j721e_sr2-hs-evm.itb"

TFA_BOARD = "generic"

OPTEEMACHINE = "k3-j721e"

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS += "cadence-mhdp-fw vxd-dec-fw ti-eth-fw"
