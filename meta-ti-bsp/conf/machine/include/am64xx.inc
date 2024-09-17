require conf/machine/include/k3.inc
require conf/machine/include/mc_k3r5.inc

SOC_FAMILY:append = ":am64xx"

KERNEL_DEVICETREE_PREFIX = "ti/k3-am642"

KERNEL_DEVICETREE = " \
    ti/k3-am642-evm-icssg1-dualemac-mii.dtbo \
    ti/k3-am642-evm-icssg1-dualemac.dtbo \
    ti/k3-am642-evm-nand.dtbo \
    ti/k3-am642-evm.dtb \
    ti/k3-am642-hummingboard-t-pcie.dtbo \
    ti/k3-am642-hummingboard-t-usb3.dtbo \
    ti/k3-am642-hummingboard-t.dtb \
    ti/k3-am642-phyboard-electra-gpio-fan.dtbo \
    ti/k3-am642-phyboard-electra-pcie-usb2.dtbo \
    ti/k3-am642-phyboard-electra-rdk.dtb \
    ti/k3-am642-sk.dtb \
    ti/k3-am642-tqma64xxl-mbax4xxl.dtb \
"

# Default tiboot3.bin on AM64x is for SR2.0 HS-FS
IMAGE_BOOT_FILES += "tiboot3-am64x_sr2-hs-fs-evm.bin"

# Since default tiboot3.bin on AM64x is for SR2.0 HS-FS, add a version for GP
IMAGE_BOOT_FILES += "tiboot3-am64x-gp-evm.bin"

# Since default tiboot3.bin on AM64x is for SR2.0 HS-FS, add a version for SR2.0 HS-SE
IMAGE_BOOT_FILES += "tiboot3-am64x_sr2-hs-evm.bin"

TFA_BOARD = "lite"

OPTEEMACHINE = "k3-am64x"

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS += "prueth-fw pruhsr-fw prusw-fw"
