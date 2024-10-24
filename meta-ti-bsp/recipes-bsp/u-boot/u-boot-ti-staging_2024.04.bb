require u-boot-ti.inc

include ${@ 'recipes-bsp/u-boot/ti-extras.inc' if d.getVar('TI_EXTRAS') else ''}

PR = "r0"

BRANCH = "ti-u-boot-2024.04"

SRCREV = "8ce3f54eee6f5d650f5ce2af8e528afeb7090b51"
