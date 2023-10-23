require conf/machine/include/k3.inc
SOC_FAMILY:append = ":j7200"

TFA_BOARD = "generic"

OPTEEMACHINE = "k3-j721e"

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS += "ti-eth-fw"
