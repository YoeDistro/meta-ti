require conf/machine/include/ti-soc.inc
SOC_FAMILY:append = ":omapl138"

require conf/machine/include/arm/armv5/tune-arm926ejs.inc

KERNEL_IMAGETYPE = "zImage"

require conf/machine/include/ti-bsp.inc

EXTRA_IMAGEDEPENDS += "virtual/bootloader"

MACHINE_ESSENTIAL_EXTRA_RDEPENDS += "kernel-image-zimage"

MACHINE_FEATURES = "serial usbhost usbgadget alsa"
