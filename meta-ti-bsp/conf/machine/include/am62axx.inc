require conf/machine/include/k3.inc
require conf/machine/include/mc_k3r5.inc

SOC_FAMILY:append = ":am62axx"

MACHINE_FEATURES += "screen touchscreen"

TFA_K3_SYSTEM_SUSPEND = "1"

TFA_BOARD = "lite"
OPTEEMACHINE = "k3-am62x"

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS += "cnm-wave-fw"

TI_CORE_INITRAMFS_KERNEL_MODULES = "kernel-module-tps6594-i2c kernel-module-tps6594-regulator kernel-module-tps6594-core"
TI_CORE_INITRAMFS_KERNEL_MODULES:bsp-ti-6_6 = ""
TI_CORE_INITRAMFS_KERNEL_MODULES:bsp-ti-6_12 = ""
