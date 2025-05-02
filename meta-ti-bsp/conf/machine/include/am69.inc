require conf/machine/include/k3.inc
require conf/machine/include/mc_k3r5.inc

SOC_FAMILY:append = ":j784s4"

MACHINE_FEATURES += "screen gpu"

require conf/machine/include/mesa-pvr.inc
PREFERRED_PROVIDER_virtual/gpudriver ?= "${BSP_ROGUE_DRIVER_PROVIDER}"

# Default tiboot3.bin on AM69 is for SR1.0 HS-FS
IMAGE_BOOT_FILES += "tiboot3-j784s4-hs-fs-evm.bin"

# Since default tiboot3.bin on AM69 is for HS-FS, add a version for SR1.0 HS-SE
IMAGE_BOOT_FILES += "tiboot3-j784s4-hs-evm.bin"

TFA_BOARD = "j784s4"

OPTEEMACHINE = "k3-j784s4"

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS += "cadence-mhdp-fw cnm-wave-fw ti-eth-fw-j784s4"
