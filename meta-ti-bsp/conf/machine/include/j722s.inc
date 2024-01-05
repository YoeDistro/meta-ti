require conf/machine/include/k3.inc
SOC_FAMILY:append = ":j722s"

MACHINE_FEATURES += "screen gpu"

require conf/machine/include/mesa-pvr.inc
PREFERRED_PROVIDER_virtual/gpudriver ?= ""

# Default tiboot3.bin on J722S is for HS-FS
IMAGE_BOOT_FILES += "tiboot3-j722s-hs-fs-evm.bin"

TFA_BOARD = "lite"
TFA_K3_SYSTEM_SUSPEND = "1"

OPTEEMACHINE = "k3-am62x"

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS += "cnm-wave-fw"

SERIAL_CONSOLES = "115200;ttyS2"
