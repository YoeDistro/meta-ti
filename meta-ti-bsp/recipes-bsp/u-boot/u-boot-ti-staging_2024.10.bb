require u-boot-ti.inc

DEFAULT_PREFERENCE = "-1"

include ${@ 'recipes-bsp/u-boot/ti-extras.inc' if d.getVar('TI_EXTRAS') else ''}

PR = "r0"

BRANCH = "ti-u-boot-2024.10"

SRCREV = "f919c3a889f0ec7d63a48b5d0ed064386b0980bd"
