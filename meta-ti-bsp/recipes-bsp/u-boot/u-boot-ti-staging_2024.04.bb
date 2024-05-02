require u-boot-ti.inc

DEFAULT_PREFERENCE = "-1"

include ${@ 'recipes-bsp/u-boot/ti-extras.inc' if d.getVar('TI_EXTRAS') else ''}

PR = "r0"

BRANCH = "ti-u-boot-2024.04"

SRCREV = "243d0c74704df56b1fab4e7989805adb45142070"
