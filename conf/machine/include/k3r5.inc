# TI K3 Armv7 profile for Cortex-R5F cores

require conf/machine/include/ti-soc.inc
SOC_FAMILY_append = ":k3r5"

# The closest of existing tunes for Cortex-R5F
DEFAULTTUNE = "armv7athf"
require conf/machine/include/tune-cortexa8.inc

# R5 runs early bootloader and loads SYSFW
# https://git.ti.com/cgit/ti-u-boot/ti-u-boot/tree/board/ti/am65x/README
# https://git.ti.com/cgit/ti-u-boot/ti-u-boot/tree/board/ti/j721e/README
PREFERRED_PROVIDER_virtual/kernel = "linux-dummy"
PREFERRED_PROVIDER_virtual/bootloader = "u-boot-ti-staging"
PREFERRED_PROVIDER_u-boot = "u-boot-ti-staging"

SPL_BINARY = ""
UBOOT_SUFFIX = "bin"
UBOOT_BINARY = "u-boot-spl.${UBOOT_SUFFIX}"
UBOOT_IMAGE = "u-boot-r5spl-${MAINMACHINE}-${PV}-${PR}.${UBOOT_SUFFIX}"
UBOOT_SYMLINK = "u-boot-r5spl.${UBOOT_SUFFIX}"

PACKAGECONFIG_pn-u-boot-ti-staging = ""
PACKAGECONFIG_pn-u-boot-ti-mainline = ""

TI_SECURE_DEV_PKG = "${TI_SECURE_DEV_PKG_K3}"
