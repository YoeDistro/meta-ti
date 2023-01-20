require conf/machine/include/k3.inc
SOC_FAMILY:append = ":am62xx"

MACHINE_FEATURES += "screen touchscreen gpu"

SERIAL_CONSOLES = "115200;ttyS2"
SERIAL_CONSOLES_CHECK = "${SERIAL_CONSOLES}"

PREFERRED_PROVIDER_virtual/egl ?= "ti-img-rogue-umlibs"
PREFERRED_PROVIDER_virtual/libgles1 ?= "ti-img-rogue-umlibs"
PREFERRED_PROVIDER_virtual/libgles2 ?= "ti-img-rogue-umlibs"
PREFERRED_PROVIDER_virtual/libgbm ?= "ti-img-rogue-umlibs"
PREFERRED_PROVIDER_virtual/gpudriver ?= "ti-img-rogue-driver"

TFA_BOARD = "lite"
TFA_K3_SYSTEM_SUSPEND = "1"

OPTEEMACHINE = "k3-am62x"
