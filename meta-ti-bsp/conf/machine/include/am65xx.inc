require conf/machine/include/k3.inc
SOC_FAMILY:append = ":am65xx"

MACHINE_FEATURES += "screen touchscreen gpu"

require conf/machine/include/mesa-pvr.inc
PREFERRED_PROVIDER_virtual/gpudriver ?= "ti-sgx-ddk-km"

KERNEL_DEVICETREE_PREFIX = "ti/k3-am654"

KERNEL_DEVICETREE = " \
    ti/k3-am654-base-board.dtb \
"

IMAGE_BOOT_FILES += "sysfw.itb"

# Default on AM65x is for GP
IMAGE_BOOT_FILES += "tiboot3-am65x_sr2-gp-evm.bin"
IMAGE_BOOT_FILES += "sysfw-am65x_sr2-gp-evm.itb"

# Since default on AM65x is for GP, add a version for HS-SE
IMAGE_BOOT_FILES += "tiboot3-am65x_sr2-hs-evm.bin"
IMAGE_BOOT_FILES += "sysfw-am65x_sr2-hs-evm.itb"

TFA_BOARD = "generic"

OPTEEMACHINE = "k3-am65x"

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS += "prueth-fw-am65x"
