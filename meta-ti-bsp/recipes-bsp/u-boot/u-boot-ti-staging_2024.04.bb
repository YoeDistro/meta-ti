require u-boot-ti.inc

DEFAULT_PREFERENCE = "-1"

include ${@ 'recipes-bsp/u-boot/ti-extras.inc' if d.getVar('TI_EXTRAS') else ''}

PR = "r0"

BRANCH = "ti-u-boot-2024.04"

SRCREV = "25049ad560826f7dc1c4740883b0016014a59789"
