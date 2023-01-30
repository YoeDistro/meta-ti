require conf/machine/include/k3.inc
SOC_FAMILY:append = ":j784s4"

PREFERRED_PROVIDER_virtual/gpudriver ?= "ti-img-rogue-driver"

TFA_BOARD = "j784s4"

OPTEEMACHINE = "k3-j784s4"
