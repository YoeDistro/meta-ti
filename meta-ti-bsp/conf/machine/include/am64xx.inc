require conf/machine/include/k3.inc
SOC_FAMILY:append = ":am64xx"

SERIAL_CONSOLES = "115200;ttyS2 115200;ttyS1"
SERIAL_CONSOLES_CHECK = "${SERIAL_CONSOLES}"

KERNEL_DEVICETREE_PREFIX = "ti/k3-am642"

KERNEL_DEVICETREE = " \
    ti/k3-am642-evm.dtb \
    ti/k3-am642-sk.dtb \
"

# Default tiboot3.bin on AM64x is for SR2.0 HS-FS
BBMULTICONFIG = "k3r5-sr2-hs-fs"
do_image_complete[mcdepends] = "mc::k3r5-sr2-hs-fs:ti-sci-fw:do_deploy"
IMAGE_BOOT_FILES += "tiboot3-am64x_sr2-hs-fs-evm.bin"

# Since default tiboot3.bin on AM64x is for SR2.0 HS-FS, add a version for GP
BBMULTICONFIG += "k3r5-gp"
do_image_complete[mcdepends] += "mc::k3r5-gp:ti-sci-fw:do_deploy"
IMAGE_BOOT_FILES += "tiboot3-am64x-gp-evm.bin"

# Since default tiboot3.bin on AM64x is for SR2.0 HS-FS, add a version for SR2.0 HS-SE
BBMULTICONFIG += "k3r5-sr2-hs-se"
do_image_complete[mcdepends] += "mc::k3r5-sr2-hs-se:ti-sci-fw:do_deploy"
IMAGE_BOOT_FILES += "tiboot3-am64x_sr2-hs-evm.bin"

TFA_BOARD = "lite"

OPTEEMACHINE = "k3-am64x"
