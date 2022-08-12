require conf/machine/include/k3.inc
SOC_FAMILY_append = ":am64xx"

MACHINE_FEATURES += "screen touchscreen"

SERIAL_CONSOLES = "115200;ttyS2 115200;ttyS1"
SERIAL_CONSOLES_CHECK = "${SERIAL_CONSOLES}"

KERNEL_DEVICETREE = " \
    ti/k3-am642-evm.dtb \
    ti/k3-am642-sk.dtb \
    ti/k3-am642-evm-icssg1-dualemac.dtbo \
    ti/k3-am642-evm-icssg1-dualemac-mii.dtbo \
    ti/k3-am642-evm-nand.dtbo \
"

BBMULTICONFIG = "k3r5-hs-se"
do_image_wic[mcdepends] = "mc::k3r5-hs-se:ti-sci-fw:do_deploy"
do_image_tar[mcdepends] = "mc::k3r5-hs-se:ti-sci-fw:do_deploy"

TFA_BOARD = "lite"
OPTEEMACHINE = "k3-am64x"
OPTEEOUTPUTMACHINE = "k3"

# Set the list of device targets for ti-pdk class recipes
TI_PDK_LIMIT_SOCS = "am64x"
TI_PDK_LIMIT_BOARDS = "am64x_evm"
TI_PDK_LIMIT_CORES = "mcu1_0 mpu1_0 mcu1_1 mcu2_0 mcu2_1"
