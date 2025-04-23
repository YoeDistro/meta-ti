require conf/machine/include/k3.inc
require conf/machine/include/mc_k3r5.inc

SOC_FAMILY:append = ":am62axx"

MACHINE_FEATURES += "screen touchscreen"

TFA_K3_SYSTEM_SUSPEND = "1"

# Default tiboot3.bin on AM62A is for HS-FS
IMAGE_BOOT_FILES += "tiboot3-am62ax-hs-fs-evm.bin"

# Since default tiboot3.bin on AM62A is for HS-FS, add a version for HS-SE
IMAGE_BOOT_FILES += "tiboot3-am62ax-hs-evm.bin"

TFA_BOARD = "lite"
OPTEEMACHINE = "k3-am62x"

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS += "cnm-wave-fw"
