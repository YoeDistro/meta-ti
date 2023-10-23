require conf/machine/include/k3.inc
SOC_FAMILY:append = ":am64xx"

KERNEL_DEVICETREE_PREFIX = "ti/k3-am642"

KERNEL_DEVICETREE = " \
    ti/k3-am642-evm.dtb \
    ti/k3-am642-sk.dtb \
"

# Default tiboot3.bin on AM64x is for SR2.0 HS-FS
IMAGE_BOOT_FILES += "tiboot3-am64x_sr2-hs-fs-evm.bin"

# Since default tiboot3.bin on AM64x is for SR2.0 HS-FS, add a version for GP
IMAGE_BOOT_FILES += "tiboot3-am64x-gp-evm.bin"

# Since default tiboot3.bin on AM64x is for SR2.0 HS-FS, add a version for SR2.0 HS-SE
IMAGE_BOOT_FILES += "tiboot3-am64x_sr2-hs-evm.bin"

TFA_BOARD = "lite"

OPTEEMACHINE = "k3-am64x"

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS += "prueth-fw-am65x-sr2 pruhsr-fw-am65x-sr2 prusw-fw-am65x-sr2"
