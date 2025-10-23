require recipes-bsp/u-boot/u-boot-ti.inc

SUMMARY = "BeagleBoard.org U-Boot"

COMPATIBLE_MACHINE = "pocketbeagle2|beagle.*"

PV = "2025.10"

UBOOT_GIT_URI = "git://github.com/beagleboard/u-boot.git"
UBOOT_GIT_PROTOCOL = "https"
BRANCH = "v2025.10-Beagle"
SRCREV = "6825d60bea17774358f4199c4c7062801cfb931e"

BRANCH:pocketbeagle2 = "v2025.10-am62-pocketbeagle2"
BRANCH:pocketbeagle2-k3r5 = "v2025.10-am62-pocketbeagle2"
SRCREV:pocketbeagle2 = "4e0b6294a1185029d34eff6ed91f3ad1734b643f"
SRCREV:pocketbeagle2-k3r5 = "4e0b6294a1185029d34eff6ed91f3ad1734b643f"

SRC_URI:append:pocketbeagle2 = " file://bootcmd-ti-mmc.cfg"
