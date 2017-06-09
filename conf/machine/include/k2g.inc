require conf/machine/include/keystone.inc
SOC_FAMILY_append = ":k2g"

# Set the list of device targets for ti-pdk class recipes
TI_PDK_LIMIT_SOCS = "k2g"
TI_PDK_LIMIT_BOARDS = "evmK2G iceK2G"
