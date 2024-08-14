require conf/machine/include/k3.inc
SOC_FAMILY:append = ":j722s"

MACHINE_FEATURES += "screen gpu"

require conf/machine/include/mesa-pvr.inc
PREFERRED_PROVIDER_virtual/gpudriver ?= "${BSP_ROGUE_DRIVER_PROVIDER}"

# Default tiboot3.bin on J722S is for HS-FS
TI_BOOTLOADERS += "tiboot3-j722s-hs-fs-evm.bin"
TI_MULTICONFIGS += "j722s-hs-fs-evm"

# Since default tiboot3.bin on J722S is for HS-FS, add a version for HS-SE
IMAGE_SR1_HS_SE_FILES = "tiboot3-j722s-hs-evm.bin"
IMAGE_SR1_HS_SE_FILES:bsp-mainline = ""
IMAGE_SR1_HS_SE_FILES:bsp-next = ""
MULTICONFIG_SR1_HS_SE = "j722s-hs-evm"
MULTICONFIG_SR1_HS_SE:bsp-mainline = ""
MULTICONFIG_SR1_HS_SE:bsp-next = ""
TI_BOOTLOADERS += "${IMAGE_SR1_HS_SE_FILES}"
TI_MULTICONFIGS += "${MULTICONFIG_SR1_HS_SE}"

TFA_BOARD = "lite"
TFA_K3_SYSTEM_SUSPEND = "1"

OPTEEMACHINE = "k3-am62x"

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS += "cnm-wave-fw"

SERIAL_CONSOLES = "115200;ttyS2"
