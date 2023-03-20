require conf/machine/include/k3.inc
SOC_FAMILY:append = ":am62xx"

MACHINE_FEATURES += "screen touchscreen"

SERIAL_CONSOLES = "115200;ttyS0 115200;ttyS2"
SERIAL_CONSOLES_CHECK = "${SERIAL_CONSOLES}"

TFA_K3_SYSTEM_SUSPEND = "1"

# Default tiboot3.bin on AM62x LP is for HS-SE
BBMULTICONFIG += "k3r5-hs-se"
do_image_wic[mcdepends] += "mc::k3r5-hs-se:ti-sci-fw:do_deploy"

TFA_BOARD = "lite"
OPTEEMACHINE = "k3-am62x"
