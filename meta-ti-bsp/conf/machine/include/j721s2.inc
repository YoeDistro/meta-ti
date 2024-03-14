require conf/machine/include/k3.inc
SOC_FAMILY:append = ":j721s2"

MACHINE_FEATURES += "screen gpu"

require conf/machine/include/mesa-pvr.inc
PREFERRED_PROVIDER_virtual/gpudriver ?= "ti-img-rogue-driver"

# Default tiboot3.bin on J721S2 is for GP
IMAGE_BOOT_FILES += "tiboot3-j721s2-gp-evm.bin"

# Since default tiboot3.bin on J721S2 is for GP, add a version for SR1.0 HS-FS
IMAGE_BOOT_FILES += "tiboot3-j721s2-hs-fs-evm.bin"

# Since default tiboot3.bin on J721S2 is for GP, add a version for SR1.0 HS-SE
IMAGE_BOOT_FILES += "tiboot3-j721s2-hs-evm.bin"

TFA_BOARD = "generic"

OPTEEMACHINE = "k3-j784s4"

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS += "cadence-mhdp-fw cnm-wave-fw"
