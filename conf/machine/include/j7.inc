require conf/machine/include/k3.inc
SOC_FAMILY_append = ":j7"

OPTEEMACHINE = "k3-j721e"
OPTEEOUTPUTMACHINE = "k3"

# Set the list of device targets for ti-pdk class recipes
TI_PDK_LIMIT_SOCS = "j721e j7200"
TI_PDK_LIMIT_BOARDS = "j721e_evm j7200_evm"
TI_PDK_LIMIT_CORES = "mpu1_0 mcu1_0 mcu1_1 mcu2_0 mcu2_1 mcu3_0 mcu3_1 c66xdsp_1 c66xdsp_2 c7x_1"
