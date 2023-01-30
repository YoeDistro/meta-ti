require conf/machine/include/k3.inc
SOC_FAMILY:append = ":am62xx"

MACHINE_FEATURES += "screen touchscreen"

SERIAL_CONSOLES = "115200;ttyS2"
SERIAL_CONSOLES_CHECK = "${SERIAL_CONSOLES}"

PREFERRED_PROVIDER_virtual/gpudriver ?= "ti-img-rogue-driver"

TFA_BOARD = "lite"
TFA_K3_SYSTEM_SUSPEND = "1"

OPTEEMACHINE = "k3-am62x"
