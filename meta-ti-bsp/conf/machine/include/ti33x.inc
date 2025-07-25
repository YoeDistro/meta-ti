require conf/machine/include/ti-soc.inc
SOC_FAMILY:append = ":ti33x"

DEFAULTTUNE ?= "armv7athf-neon"
require conf/machine/include/arm/armv7a/tune-cortexa8.inc

require conf/machine/include/ti-bsp.inc

# Graphics providers and variables
require conf/machine/include/mesa-pvr.inc
PREFERRED_PROVIDER_virtual/gpudriver ?= "${BSP_SGX_DRIVER_PROVIDER}"
PVR_DISPLAY_CONTROLLER_ALIAS ?= "tilcdc"

KERNEL_IMAGETYPE = "zImage"

KERNEL_DEVICETREE_PREFIX = " \
    am335x \
    ti/omap/am335x \
"

KERNEL_DEVICETREE = " \
    ti/omap/am335x-baltos-ir2110.dtb \
    ti/omap/am335x-baltos-ir3220.dtb \
    ti/omap/am335x-baltos-ir5221.dtb \
    ti/omap/am335x-base0033.dtb \
    ti/omap/am335x-bone.dtb \
    ti/omap/am335x-boneblack-wireless.dtb \
    ti/omap/am335x-boneblack.dtb \
    ti/omap/am335x-boneblue.dtb \
    ti/omap/am335x-bonegreen-wireless.dtb \
    ti/omap/am335x-bonegreen.dtb \
    ti/omap/am335x-chiliboard.dtb \
    ti/omap/am335x-cm-t335.dtb \
    ti/omap/am335x-evm.dtb \
    ti/omap/am335x-evmsk.dtb \
    ti/omap/am335x-guardian.dtb \
    ti/omap/am335x-icev2.dtb \
    ti/omap/am335x-lxm.dtb \
    ti/omap/am335x-moxa-uc-2101.dtb \
    ti/omap/am335x-moxa-uc-8100-me-t.dtb \
    ti/omap/am335x-myirtech-myd.dtb \
    ti/omap/am335x-nano.dtb \
    ti/omap/am335x-netcan-plus-1xx.dtb \
    ti/omap/am335x-netcom-plus-2xx.dtb \
    ti/omap/am335x-netcom-plus-8xx.dtb \
    ti/omap/am335x-osd3358-sm-red.dtb \
    ti/omap/am335x-pdu001.dtb \
    ti/omap/am335x-pepper.dtb \
    ti/omap/am335x-phycore-rdk.dtb \
    ti/omap/am335x-pocketbeagle.dtb \
    ti/omap/am335x-regor-rdk.dtb \
    ti/omap/am335x-sancloud-bbe-extended-wifi.dtb \
    ti/omap/am335x-sancloud-bbe-lite.dtb \
    ti/omap/am335x-sancloud-bbe.dtb \
    ti/omap/am335x-sbc-t335.dtb \
    ti/omap/am335x-shc.dtb \
    ti/omap/am335x-sl50.dtb \
    ti/omap/am335x-wega-rdk.dtb \
"

UBOOT_ARCH = "arm"
UBOOT_MACHINE = "am335x_evm_config"

UBOOT_ENTRYPOINT = "0x80008000"
UBOOT_LOADADDRESS = "0x80008000"

# Generate an extlinux.conf file
UBOOT_EXTLINUX = "1"
UBOOT_EXTLINUX_ROOT = "root=PARTUUID=${uuid}"
UBOOT_EXTLINUX_KERNEL_ARGS = "rootwait rw earlycon"
UBOOT_EXTLINUX_BOOT_FILES = " \
    extlinux.conf;extlinux/extlinux.conf \
    ${KERNEL_IMAGETYPE} \
    ${DEVICETREE_FILES} \
"

SPL_BINARY = "MLO"
UBOOT_SUFFIX = "img"

# Use the expected value of the ubifs filesystem's volume name in the kernel
# and u-boot.
UBI_VOLNAME = "rootfs"

EXTRA_IMAGEDEPENDS += "virtual/bootloader"

MACHINE_ESSENTIAL_EXTRA_RDEPENDS += "kernel-image-zimage"

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS += "amx3-cm3 prueth-fw pruhsr-fw pruprp-fw"

# List common SoC features, may need to add touchscreen for specific machines
MACHINE_FEATURES = "apm usbgadget usbhost vfat ext2 alsa gpu"

IMAGE_FSTYPES += "tar.xz wic.xz wic.bmap"
WKS_FILE ?= "sdimage-2part.wks"
IMAGE_BOOT_FILES ?= "${SPL_BINARY} u-boot.${UBOOT_SUFFIX} ${UBOOT_EXTLINUX_BOOT_FILES}"
do_image_wic[depends] += "virtual/bootloader:do_deploy"
