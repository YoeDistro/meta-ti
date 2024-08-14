require conf/machine/include/k3.inc
SOC_FAMILY:append = ":j742s2"

MACHINE_FEATURES += "screen gpu"

require conf/machine/include/mesa-pvr.inc
PREFERRED_PROVIDER_virtual/gpudriver ?= "${BSP_ROGUE_DRIVER_PROVIDER}"

# Default tiboot3.bin on J742S2 is for HS-FS
TI_BOOTLOADERS += "tiboot3-j742s2-hs-fs-evm.bin"
TI_MULTICONFIGS += "j742s2-hs-fs-evm"

# Since default tiboot3.bin on J742S2 is for HS-FS, add a version for SR1.0 HS-SE
TI_BOOTLOADERS += "tiboot3-j742s2-hs-evm.bin"
TI_MULTICONFIGS += "j742s2-hs-evm"

TFA_BOARD = "j784s4"

OPTEEMACHINE = "k3-j784s4"

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS += "cadence-mhdp-fw cnm-wave-fw"
