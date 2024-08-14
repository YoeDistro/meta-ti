require conf/machine/include/k3.inc
SOC_FAMILY:append = ":j784s4"

MACHINE_FEATURES += "screen gpu"

require conf/machine/include/mesa-pvr.inc
PREFERRED_PROVIDER_virtual/gpudriver ?= "${BSP_ROGUE_DRIVER_PROVIDER}"

# Default tiboot3.bin on J784S4 is for GP
TI_BOOTLOADERS += "tiboot3-j784s4-gp-evm.bin"
TI_MULTICONFIGS += "j784s4-gp-evm"

# Since default tiboot3.bin on J784S4 is for GP, add a version for SR1.0 HS-FS
TI_BOOTLOADERS += "tiboot3-j784s4-hs-fs-evm.bin"
TI_MULTICONFIGS += "j784s4-hs-fs-evm"

# Since default tiboot3.bin on J784S4 is for GP, add a version for SR1.0 HS-SE
TI_BOOTLOADERS += "tiboot3-j784s4-hs-evm.bin"
TI_MULTICONFIGS += "j784s4-hs-evm"

TFA_BOARD = "j784s4"

OPTEEMACHINE = "k3-j784s4"

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS += "cadence-mhdp-fw cnm-wave-fw ti-eth-fw"
