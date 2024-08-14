require conf/machine/include/k3.inc
SOC_FAMILY:append = ":j721s2"

MACHINE_FEATURES += "screen gpu"

require conf/machine/include/mesa-pvr.inc
PREFERRED_PROVIDER_virtual/gpudriver ?= "${BSP_ROGUE_DRIVER_PROVIDER}"

# Default tiboot3.bin on J721S2 is for GP
TI_BOOTLOADERS += "tiboot3-j721s2-gp-evm.bin"
TI_MULTICONFIGS += "j721s2-gp-evm"

# Since default tiboot3.bin on J721S2 is for GP, add a version for SR1.0 HS-FS
TI_BOOTLOADERS += "tiboot3-j721s2-hs-fs-evm.bin"
TI_MULTICONFIGS += "j721s2-hs-fs-evm"

# Since default tiboot3.bin on J721S2 is for GP, add a version for SR1.0 HS-SE
TI_BOOTLOADERS += "tiboot3-j721s2-hs-evm.bin"
TI_MULTICONFIGS += "j721s2-hs-evm"

TFA_BOARD = "generic"

OPTEEMACHINE = "k3-j784s4"

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS += "cadence-mhdp-fw cnm-wave-fw"
