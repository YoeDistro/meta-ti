require conf/machine/include/k3.inc
SOC_FAMILY_append = ":am62xx"

MACHINE_FEATURES += "screen touchscreen"

SERIAL_CONSOLES = "115200;ttyS2"
SERIAL_CONSOLES_CHECK = "${SERIAL_CONSOLES}"

TFA_K3_SYSTEM_SUSPEND = "1"

# Default tiboot3.bin on AM62x is for HS-FS
BBMULTICONFIG = "k3r5-hs-fs"
do_image_wic[mcdepends] = "mc::k3r5-hs-fs:ti-sci-fw:do_deploy"
do_image_tar[mcdepends] = "mc::k3r5-hs-fs:ti-sci-fw:do_deploy"

# Since default tiboot3.bin on AM62x is for HS-FS, add a version for GP
BBMULTICONFIG += "k3r5-gp"
IMAGE_BOOT_FILES += " tiboot3-am62x-gp-evm.bin"
do_image_wic[mcdepends] += "mc::k3r5-gp:ti-sci-fw:do_deploy"
do_image_tar[mcdepends] += "mc::k3r5-gp:ti-sci-fw:do_deploy"

# Since default tiboot3.bin on AM62x is for HS-FS, add a version for HS-SE
BBMULTICONFIG += "k3r5-hs-se"
IMAGE_BOOT_FILES += " tiboot3-am62x-hs-evm.bin"
do_image_wic[mcdepends] += "mc::k3r5-hs-se:ti-sci-fw:do_deploy"
do_image_tar[mcdepends] += "mc::k3r5-hs-se:ti-sci-fw:do_deploy"

TFA_BOARD = "lite"
OPTEEMACHINE = "k3-am62x"
OPTEEOUTPUTMACHINE = "k3"
