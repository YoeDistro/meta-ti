require conf/machine/include/k3.inc
SOC_FAMILY_append = ":am65xx"

# Set the list of device targets for ti-pdk class recipes
TI_PDK_LIMIT_SOCS = "am65xx"
TI_PDK_LIMIT_BOARDS = "am65xx_evm am65xx_idk"
TI_PDK_LIMIT_CORES = "mcu1_0 mpu1_0"
