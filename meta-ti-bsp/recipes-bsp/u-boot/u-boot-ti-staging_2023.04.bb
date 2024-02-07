require u-boot-ti.inc

include ${@ 'recipes-bsp/u-boot/ti-extras.inc' if d.getVar('TI_EXTRAS') else ''}

PR = "r0"

BRANCH = "ti-u-boot-2023.04"

SRCREV = "28eaa6eb15204cb3e3cc8ed938341392c62dc218"
