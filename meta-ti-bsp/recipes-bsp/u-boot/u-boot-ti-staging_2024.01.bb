require u-boot-ti.inc

DEFAULT_PREFERENCE = "-1"

include ${@ 'recipes-bsp/u-boot/ti-extras.inc' if d.getVar('TI_EXTRAS') else ''}

PR = "r0"

BRANCH = "ti-u-boot-2024.01"

SRCREV = "2f0282922b2c458eea7f85c500a948a587437b63"
