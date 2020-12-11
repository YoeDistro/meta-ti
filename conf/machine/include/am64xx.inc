require conf/machine/include/k3.inc
SOC_FAMILY_append = ":am64xx"

MACHINE_FEATURES += "screen touchscreen gpu"

SERIAL_CONSOLES = "115200;ttyS2 115200;ttyS1"
SERIAL_CONSOLES_CHECK = "${SERIAL_CONSOLES}"

KERNEL_DEVICETREE = " \
    ti/k3-am642-evm.dtb \
"

#IMAGE_BOOT_FILES += "sysfw-am64x-evm.itb"

OPTEEMACHINE = "k3-am65x"
OPTEEOUTPUTMACHINE = "k3"

# Set the list of device targets for ti-pdk class recipes
TI_PDK_LIMIT_SOCS = "am64x"
TI_PDK_LIMIT_BOARDS = "am64xx_evm"
TI_PDK_LIMIT_CORES = "mcu1_0 mpu1_0"
