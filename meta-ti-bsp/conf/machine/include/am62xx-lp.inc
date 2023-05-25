require conf/machine/include/k3.inc
SOC_FAMILY:append = ":am62xx"

MACHINE_FEATURES += "screen touchscreen"

TFA_K3_SYSTEM_SUSPEND = "1"

# Default tiboot3.bin on AM62x LP is for HS-FS
IMAGE_BOOT_FILES += "tiboot3-am62x-hs-fs-evm.bin"

# Since default tiboot3.bin on AM62x LP is for HS-FS, add a version for HS-SE
IMAGE_BOOT_FILES += "tiboot3-am62x-hs-evm.bin"

TFA_BOARD = "lite"
OPTEEMACHINE = "k3-am62x"
