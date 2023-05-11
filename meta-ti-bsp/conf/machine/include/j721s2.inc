require conf/machine/include/k3.inc
SOC_FAMILY:append = ":j721s2"

MACHINE_FEATURES += "screen gpu"

require conf/machine/include/mesa-pvr.inc
PREFERRED_PROVIDER_virtual/gpudriver ?= "ti-img-rogue-driver"

TFA_BOARD = "generic"

OPTEEMACHINE = "k3-j784s4"
