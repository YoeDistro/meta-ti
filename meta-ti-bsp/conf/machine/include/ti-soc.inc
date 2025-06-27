# This is a generic TI SOC family. It is a superset of all other SOCs
# and platforms defined in meta-ti to allow BSP-level overrides.
SOC_FAMILY = "ti-soc"
require conf/machine/include/soc-family.inc

# kernel, initrd load addresses for the fitImage in all TI platforms
UBOOT_ENTRYPOINT = "0x82000000"
UBOOT_LOADADDRESS = "0x82000000"
UBOOT_RD_LOADADDRESS = "0x84000000"
UBOOT_RD_ENTRYPOINT = "0x84000000"

# TI platforms all use devicetrees with overlays
MACHINE_ESSENTIAL_EXTRA_RDEPENDS += "kernel-devicetree"
