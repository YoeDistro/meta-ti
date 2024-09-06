require conf/machine/include/k3.inc
require conf/machine/include/mc_k3r5.inc

SOC_FAMILY:append = ":j721s2"

MACHINE_FEATURES += "screen gpu"

require conf/machine/include/mesa-pvr.inc
PREFERRED_PROVIDER_virtual/gpudriver ?= "${BSP_ROGUE_DRIVER_PROVIDER}"

# Default tiboot3.bin on AM68 is for SR1.0 HS-FS
IMAGE_BOOT_FILES += "tiboot3-j721s2-hs-fs-evm.bin"

# Since default tiboot3.bin on AM68 is for HS-FS, add a version for SR1.0 HS-SE
IMAGE_BOOT_FILES += "tiboot3-j721s2-hs-evm.bin"

TFA_BOARD = "generic"

OPTEEMACHINE = "k3-j784s4"

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS += "cadence-mhdp-fw cnm-wave-fw"
