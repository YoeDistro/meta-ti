require recipes-bsp/u-boot/u-boot-ti.inc

SUMMARY = "BeagleBoard.org U-Boot"

COMPATIBLE_MACHINE = "pocketbeagle2|beagle.*"

PV = "2025.07"

UBOOT_GIT_URI = "git://github.com/beagleboard/u-boot.git"
UBOOT_GIT_PROTOCOL = "https"
BRANCH = "v2025.07-Beagle"
SRCREV = "919ec3cd84f39e6960d1705d4ba7f810bc1067fc"

BRANCH:pocketbeagle2 = "v2025.07-am6232-pocketbeagle2"
BRANCH:pocketbeagle2-k3r5 = "v2025.07-am6232-pocketbeagle2"
SRCREV:pocketbeagle2 = "6d5b4abc28f036a4a83ccb234f04d72eceafdc4e"
SRCREV:pocketbeagle2-k3r5 = "6d5b4abc28f036a4a83ccb234f04d72eceafdc4e"

SRC_URI:append:pocketbeagle2 = " file://bootcmd-ti-mmc.cfg"
