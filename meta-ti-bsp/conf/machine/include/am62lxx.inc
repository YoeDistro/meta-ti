require conf/machine/include/k3.inc
SOC_FAMILY:append = ":am62lxx"

MACHINE_FEATURES += "screen"

# Default tiboot3.bin on AM62L is for HS-FS
IMAGE_BOOT_FILES += "tiboot3-am62lx-hs-fs-evm.bin"

TFA_BOARD = "am62l"
TFA_K3_SYSTEM_SUSPEND = "1"

OPTEEMACHINE = "k3-am62lx"
