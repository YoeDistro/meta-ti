require conf/machine/include/k3.inc
SOC_FAMILY:append = ":am62axx"

MACHINE_FEATURES += "screen touchscreen"

TFA_K3_SYSTEM_SUSPEND = "1"

# Default tiboot3.bin on AM62A is for HS-FS
TI_BOOTLOADERS += "tiboot3-am62ax-hs-fs-evm.bin"
TI_MULTICONFIGS += "am62ax-hs-fs-evm"

# Since default tiboot3.bin on AM62A is for HS-FS, add a version for GP
TI_BOOTLOADERS += "tiboot3-am62ax-gp-evm.bin"
TI_MULTICONFIGS += "am62ax-gp-evm"

# Since default tiboot3.bin on AM62A is for HS-FS, add a version for HS-SE
TI_BOOTLOADERS += "tiboot3-am62ax-hs-evm.bin"
TI_MULTICONFIGS += "am62ax-hs-evm"

TFA_BOARD = "lite"
OPTEEMACHINE = "k3-am62x"

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS += "cnm-wave-fw"
