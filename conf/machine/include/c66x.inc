require conf/machine/include/keystone.inc
SOC_FAMILY_append = ":c66x"

# HACK: The c66x family is composed of devices with only DSP cores, and no ARM
#       cores. The Linux kernel is not supported on these machines.
PREFERRED_PROVIDER_virtual/kernel ?= "linux-dummy"
EXTRA_IMAGEDEPENDS = ""

IMAGE_FSTYPES = "tar.xz"
