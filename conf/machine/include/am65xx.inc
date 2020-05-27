require conf/machine/include/k3.inc
SOC_FAMILY_append = ":am65xx"

MACHINE_FEATURES += "touchscreen"

SERIAL_CONSOLES = "115200;ttyS2 115200;ttyS1"
SERIAL_CONSOLES_CHECK = "${SERIAL_CONSOLES}"

KERNEL_DEVICETREE = " \
    ti/k3-am654-base-board.dtb \
    ti/k3-am654-base-board-sr1.dtb \
    ti/k3-am654-gp.dtbo \
    ti/k3-am654-idk.dtbo \
    ti/k3-am654-idk-sr1.dtbo \
    ti/k3-am654-evm-hdmi.dtbo \
    ti/k3-am654-evm-oldi-lcd1evm.dtbo \
    ti/k3-am654-evm-tc358876.dtbo \
    ti/k3-am654-pcie-usb2.dtbo \
    ti/k3-am654-pcie-usb3.dtbo \
    ti/k3-am654-base-board-jailhouse.dtbo \
    ti/k3-am654-evm-prupwm.dtbo \
"

OPTEEMACHINE = "k3"
OPTEEFLAVOR = "am65x"
OPTEEOUTPUTMACHINE = "k3"

# Set the list of device targets for ti-pdk class recipes
TI_PDK_LIMIT_SOCS = "am65xx"
TI_PDK_LIMIT_BOARDS = "am65xx_evm am65xx_idk"
TI_PDK_LIMIT_CORES = "mcu1_0 mpu1_0"
