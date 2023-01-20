require conf/machine/include/k3.inc
SOC_FAMILY:append = ":am65xx"

MACHINE_FEATURES += "screen touchscreen gpu"

SERIAL_CONSOLES = "115200;ttyS2 115200;ttyS1"
SERIAL_CONSOLES_CHECK = "${SERIAL_CONSOLES}"

PREFERRED_PROVIDER_virtual/egl ?= "ti-sgx-ddk-um"
PREFERRED_PROVIDER_virtual/libgles1 ?= "ti-sgx-ddk-um"
PREFERRED_PROVIDER_virtual/libgles2 ?= "ti-sgx-ddk-um"
PREFERRED_PROVIDER_virtual/libgbm ?= "ti-sgx-ddk-um"
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

BBMULTICONFIG += "k3r5-sr2"

# Since J721e does not support multi-cert tiboot3.bin add standalone sysfw.itb
IMAGE_BOOT_FILES += "sysfw.itb"

# Since default sysfw.itb on AM65x is for SR2.0, add a version for SR1.0
IMAGE_BOOT_FILES += "sysfw-am65x-evm.itb"

do_image_wic[mcdepends] += "mc::k3r5-sr2:ti-sci-fw:do_deploy"
do_image_tar[mcdepends] += "mc::k3r5-sr2:ti-sci-fw:do_deploy"

TFA_BOARD = "generic"

OPTEEMACHINE = "k3-am65x"
