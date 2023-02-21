require conf/machine/include/k3.inc
SOC_FAMILY:append = ":am65xx"

MACHINE_FEATURES += "screen touchscreen"

SERIAL_CONSOLES = "115200;ttyS2 115200;ttyS1"
SERIAL_CONSOLES_CHECK = "${SERIAL_CONSOLES}"

PREFERRED_PROVIDER_virtual/gpudriver ?= "ti-sgx-ddk-km"

KERNEL_DEVICETREE = " \
    ti/k3-am654-base-board.dtb \
    ti/k3-am654-base-board-sr1.dtbo \
    ti/k3-am654-gp.dtbo \
    ti/k3-am654-idk.dtbo \
    ti/k3-am654-idk-sr1.dtbo \
    ti/k3-am654-sr1.dtbo \
    ti/k3-am654-pcie-usb2.dtbo \
    ti/k3-am654-pcie-usb3.dtbo \
    ti/k3-am654-evm-tc358876.dtbo \
    ti/k3-am654-evm-oldi-lcd1evm.dtbo \
    ti/k3-am654-evm-ov5640.dtbo \
"

# On AM65x the file tiboot3.bin comes from the bootloader not ti-sci-fw
do_image_wic[mcdepends] += "mc::k3r5:virtual/bootloader:do_deploy"

# Default sysfw.itb on AM65x is for SR2.0
BBMULTICONFIG += "k3r5-sr2"
do_image_wic[mcdepends] += "mc::k3r5-sr2:ti-sci-fw:do_deploy"
IMAGE_BOOT_FILES += "sysfw.itb"

# Since default sysfw.itb on AM65x is for SR2.0, add a version for SR1.0
IMAGE_BOOT_FILES += "sysfw-am65x-evm.itb"

TFA_BOARD = "generic"

OPTEEMACHINE = "k3-am65x"
