# This is a generic TI SOC family. It is a superset of all other SOCs
# and platforms defined in meta-ti to allow BSP-level overrides.
SOC_FAMILY = "ti-soc"

# TI platforms all use devicetrees with overlays
MACHINE_ESSENTIAL_EXTRA_RDEPENDS += "kernel-devicetree"
KERNEL_DTB_OVERLAY_SUPPORT ?= "1"

require conf/machine/include/soc-family.inc
