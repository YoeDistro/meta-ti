require conf/machine/include/k3.inc
SOC_FAMILY:append = ":j721e"

MACHINE_FEATURES += "gpu"

PREFERRED_PROVIDER_virtual/egl ?= "ti-img-rogue-umlibs"
PREFERRED_PROVIDER_virtual/libgles1 ?= "ti-img-rogue-umlibs"
PREFERRED_PROVIDER_virtual/libgles2 ?= "ti-img-rogue-umlibs"
PREFERRED_PROVIDER_virtual/libgbm ?= "ti-img-rogue-umlibs"
PREFERRED_PROVIDER_virtual/gpudriver ?= "ti-img-rogue-driver"

# On J721e the file tiboot3.bin comes from the bootloader not ti-sci-fw
do_image_wic[mcdepends] += "mc::k3r5:virtual/bootloader:do_deploy"

IMAGE_BOOT_FILES += "sysfw.itb"

TFA_BOARD = "generic"

OPTEEMACHINE = "k3-j721e"
