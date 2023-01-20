require conf/machine/include/k3.inc
SOC_FAMILY:append = ":j721e"

OPTEEMACHINE = "k3-j721e"
OPTEEOUTPUTMACHINE = "k3"

MACHINE_FEATURES += "gpu"

PREFERRED_PROVIDER_virtual/egl ?= "ti-img-rogue-umlibs"
PREFERRED_PROVIDER_virtual/libgles1 ?= "ti-img-rogue-umlibs"
PREFERRED_PROVIDER_virtual/libgles2 ?= "ti-img-rogue-umlibs"
PREFERRED_PROVIDER_virtual/libgbm ?= "ti-img-rogue-umlibs"
PREFERRED_PROVIDER_virtual/gpudriver ?= "ti-img-rogue-driver"
