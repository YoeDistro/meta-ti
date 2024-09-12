require conf/machine/include/k3.inc
require conf/machine/include/mc_k3r5.inc

SOC_FAMILY:append = ":am62pxx"

MACHINE_FEATURES += "screen gpu"

require conf/machine/include/mesa-pvr.inc
PREFERRED_PROVIDER_virtual/gpudriver ?= "${BSP_ROGUE_DRIVER_PROVIDER}"

# Default tiboot3.bin on AM62Px is for HS-FS
IMAGE_BOOT_FILES += "tiboot3-am62px-hs-fs-evm.bin"

TFA_BOARD = "lite"
TFA_K3_SYSTEM_SUSPEND = "1"

OPTEEMACHINE = "k3-am62x"

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS += "cnm-wave-fw"

SERIAL_CONSOLES = "115200;ttyS2"

# Bitmap image tarball for early splashscreen
IMAGE_BOOT_FILES += "ti_logo_414x97_32bpp.bmp.gz"
