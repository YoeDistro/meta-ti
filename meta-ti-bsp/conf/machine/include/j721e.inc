require conf/machine/include/k3.inc
SOC_FAMILY:append = ":j721e"

PREFERRED_PROVIDER_virtual/gpudriver ?= "ti-img-rogue-driver"

# On J721e the file tiboot3.bin comes from the bootloader not ti-sci-fw
do_image_wic[mcdepends] += "mc::k3r5:virtual/bootloader:do_deploy"

IMAGE_BOOT_FILES += "sysfw.itb"

TFA_BOARD = "generic"

OPTEEMACHINE = "k3-j721e"
