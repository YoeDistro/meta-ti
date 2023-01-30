require conf/machine/include/k3.inc
SOC_FAMILY:append = ":j721s2"

PREFERRED_PROVIDER_virtual/gpudriver ?= "ti-img-rogue-driver"

TFA_BOARD = "generic"

OPTEEMACHINE = "k3-j784s4"
