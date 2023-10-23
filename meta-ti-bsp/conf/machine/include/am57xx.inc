require conf/machine/include/omap-a15.inc
SOC_FAMILY:append = ":dra7xx"
SOC_FAMILY:append = ":am57xx"

MACHINE_FEATURES += "pci"

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS += "prueth-fw prusw-fw pruhsr-fw pruprp-fw"
