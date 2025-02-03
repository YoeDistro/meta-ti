require u-boot-ti.inc

include ${@ 'recipes-bsp/u-boot/ti-extras.inc' if d.getVar('TI_EXTRAS') else ''}

PR = "r0"

BRANCH = "ti-u-boot-2025.01"

SRC_URI += "file://0001-remoteproc-k3-m4-Fix-implicit-definition-of-ti_secur.patch"

SRCREV = "4e9c90ad8b12cc5b4fdd1308bc3ac3e4fc869aab"
