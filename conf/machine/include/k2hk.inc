require conf/machine/include/keystone.inc
SOC_FAMILY_append = ":k2hk"

# Set the list of device targets for ti-pdk class recipes
TI_PDK_LIMIT_SOCS = "k2h k2k"
TI_PDK_LIMIT_BOARDS = "evmK2H evmK2K"
