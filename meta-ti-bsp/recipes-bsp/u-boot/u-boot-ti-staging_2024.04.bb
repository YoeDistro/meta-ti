require u-boot-ti.inc

DEFAULT_PREFERENCE = "-1"

include ${@ 'recipes-bsp/u-boot/ti-extras.inc' if d.getVar('TI_EXTRAS') else ''}

PR = "r0"

BRANCH = "ti-u-boot-2024.04"

SRCREV = "d49fa3defa50c6d3f04acbb52fd486c13c14ab6a"
