require u-boot-ti.inc

include ${@ 'recipes-bsp/u-boot/ti-extras.inc' if d.getVar('TI_EXTRAS') else ''}

PR = "r0"

BRANCH = "ti-u-boot-2025.01"

SRCREV = "cd91d73601810374d16a1f17505ab2e72e31f75d"
