require u-boot-ti.inc

include ${@ 'recipes-bsp/u-boot/ti-extras.inc' if d.getVar('TI_EXTRAS') else ''}

PR = "r0"

BRANCH = "ti-u-boot-2025.01-next"

SRCREV = "894a9fd5868ccf51f0509b79cbc3b1af150afbe8"
