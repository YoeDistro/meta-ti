require conf/machine/include/k3.inc
require conf/machine/include/mc_k3r5.inc

SOC_FAMILY:append = ":j722s"

MACHINE_FEATURES += "screen gpu"

require conf/machine/include/mesa-pvr.inc
PREFERRED_PROVIDER_virtual/gpudriver ?= "${BSP_ROGUE_DRIVER_PROVIDER}"

# Default tiboot3.bin on J722S is for HS-FS
IMAGE_BOOT_FILES += "tiboot3-j722s-hs-fs-evm.bin"

# Since default tiboot3.bin on J722S is for HS-FS, add a version for HS-SE
IMAGE_SR1_HS_SE_FILES = "tiboot3-j722s-hs-evm.bin"
IMAGE_SR1_HS_SE_FILES:bsp-mainline = ""
IMAGE_SR1_HS_SE_FILES:bsp-next = ""
IMAGE_BOOT_FILES += "${IMAGE_SR1_HS_SE_FILES}"

TFA_BOARD = "lite"
TFA_K3_SYSTEM_SUSPEND = "1"

OPTEEMACHINE = "k3-am62x"

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS += "cnm-wave-fw"

SERIAL_CONSOLES = "115200;ttyS2"
