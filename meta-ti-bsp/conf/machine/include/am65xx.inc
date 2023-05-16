require conf/machine/include/k3.inc
SOC_FAMILY:append = ":am65xx"

MACHINE_FEATURES += "screen touchscreen gpu"

require conf/machine/include/mesa-pvr.inc
PREFERRED_PROVIDER_virtual/gpudriver ?= ""

KERNEL_DEVICETREE_PREFIX = "ti/k3-am654"

KERNEL_DEVICETREE = " \
    ti/k3-am654-base-board.dtb \
"

# On AM65x the file tiboot3.bin comes from the bootloader not ti-sci-fw
do_image_complete[mcdepends] += "mc::k3r5:virtual/bootloader:do_deploy"

# Default sysfw.itb on AM65x is for SR2.0
BBMULTICONFIG += "k3r5-sr2"
do_image_complete[mcdepends] += "mc::k3r5-sr2:ti-sci-fw:do_deploy"
IMAGE_BOOT_FILES += "sysfw.itb sysfw-am65x_sr2-${SYSFW_SUFFIX}-evm.itb"

# Since default sysfw.itb on AM65x is for SR2.0, add a version for SR1.0
IMAGE_BOOT_FILES += "sysfw-am65x-${SYSFW_SUFFIX}-evm.itb"

TFA_BOARD = "generic"

OPTEEMACHINE = "k3-am65x"
