require conf/machine/include/k3.inc
SOC_FAMILY:append = ":am62lxx"

MACHINE_FEATURES += "screen"

TFA_BOARD = "am62lx"
TFA_K3_SYSTEM_SUSPEND = "1"
TFA_PLATFORM = "k3low"

OPTEEMACHINE = "k3-am62lx"
