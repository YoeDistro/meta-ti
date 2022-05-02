require conf/machine/include/k3.inc
SOC_FAMILY:append = ":am62xx"

MACHINE_FEATURES += "screen touchscreen"

SERIAL_CONSOLES = "115200;ttyS2"
SERIAL_CONSOLES_CHECK = "${SERIAL_CONSOLES}"

# AM62 supports multi-certificate images, use the same
IMAGE_BOOT_FILES = "${IMAGE_BOOT_FILES_MULTI_CERT}"

do_image_wic[mcdepends] = "mc::k3r5:ti-sci-fw:do_deploy"
do_image_tar[mcdepends] = "mc::k3r5:ti-sci-fw:do_deploy"

TFA_BOARD = "lite"
OPTEEMACHINE = "k3-am65x"
OPTEEOUTPUTMACHINE = "k3"
