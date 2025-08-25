require conf/machine/include/k3.inc
require conf/machine/include/mc_k3r5.inc

SOC_FAMILY:append = ":am65xx"

MACHINE_FEATURES += "screen touchscreen gpu"

require conf/machine/include/mesa-pvr.inc
PREFERRED_PROVIDER_virtual/gpudriver ?= "${BSP_SGX_DRIVER_PROVIDER}"

KERNEL_DEVICETREE_PREFIX = "ti/k3-am654"

KERNEL_DEVICETREE_DTBMERGE = " \
    ti/k3-am654-gp-evm.dtb \
    ti/k3-am654-evm.dtb \
    ti/k3-am654-idk.dtb \
    ti/k3-am6548-iot2050-advanced-m2-bkey-ekey-pcie.dtb \
    ti/k3-am6548-iot2050-advanced-m2-bkey-usb3.dtb \
"

KERNEL_DEVICETREE = " \
    ti/k3-am654-base-board-rocktech-rk101-panel.dtbo \
    ti/k3-am654-base-board.dtb \
    ti/k3-am654-icssg2.dtbo \
    ti/k3-am654-idk.dtbo \
    ti/k3-am654-pcie-usb2.dtbo \
    ti/k3-am654-pcie-usb3.dtbo \
    ti/k3-am6548-iot2050-advanced-m2-bkey-ekey-pcie.dtbo \
    ti/k3-am6548-iot2050-advanced-m2-bkey-usb3.dtbo \
    ti/k3-am6548-iot2050-advanced-m2.dtb \
    ti/k3-am6548-iot2050-advanced-pg2.dtb \
    ti/k3-am6548-iot2050-advanced-sm.dtb \
    ti/k3-am6548-iot2050-advanced.dtb \
"

# Deploy SYSFW and any variants
IMAGE_BOOT_FILES += "sysfw.itb sysfw-*-evm.itb"

TFA_BOARD = "generic"

OPTEEMACHINE = "k3-am65x"

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS += "prueth-fw"
