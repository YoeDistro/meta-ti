require conf/machine/include/keystone.inc
SOC_FAMILY_append = ":k2e"

# Set the list of device targets for ti-pdk class recipes
TI_PDK_LIMIT_SOCS = "k2e"
TI_PDK_LIMIT_BOARDS = "evmK2E"
