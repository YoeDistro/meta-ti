# TI K3 Armv7 profile for Cortex-R5F cores

require conf/machine/include/ti-soc.inc
SOC_FAMILY:append = ":k3r5"

# The closest of existing tunes for Cortex-R5F
DEFAULTTUNE = "armv7athf"
require conf/machine/include/arm/armv7a/tune-cortexa8.inc

# R5 runs early bootloader and loads SYSFW
# https://git.ti.com/cgit/ti-u-boot/ti-u-boot/tree/doc/board/ti/j721e_evm.rst
# https://git.ti.com/cgit/ti-u-boot/ti-u-boot/tree/doc/board/ti/am62x_sk.rst
# https://git.ti.com/cgit/ti-u-boot/ti-u-boot/tree/doc/board/ti/k3.rst

require conf/machine/include/ti-bsp.inc
PREFERRED_PROVIDER_virtual/kernel = "linux-dummy"

SPL_SUFFIX = "bin"
SPL_BINARY = "tiboot3-${SYSFW_SOC}-${SYSFW_SUFFIX}-${SYSFW_CONFIG}.${SPL_SUFFIX}"
SPL_SYMLINK = "tiboot3.${SPL_SUFFIX}"
UBOOT_SUFFIX = "bin"
UBOOT_BINARY = "u-boot-r5spl.${UBOOT_SUFFIX}"
UBOOT_IMAGE = "u-boot-r5spl-${MAINMACHINE}-${PV}-${PR}.${UBOOT_SUFFIX}"
UBOOT_SYMLINK = "u-boot-r5spl-${MAINMACHINE}.${UBOOT_SUFFIX}"

PACKAGECONFIG:pn-u-boot-ti-staging = ""
PACKAGECONFIG:pn-u-boot-ti-mainline = ""
