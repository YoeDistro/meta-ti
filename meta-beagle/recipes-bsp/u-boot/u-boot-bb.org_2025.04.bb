require recipes-bsp/u-boot/u-boot-ti.inc

SUMMARY = "BeagleBoard.org U-Boot"

COMPATIBLE_MACHINE = "pocketbeagle2|beagle.*"

PV = "2025.04"

UBOOT_GIT_URI = "git://github.com/beagleboard/u-boot.git"
UBOOT_GIT_PROTOCOL = "https"
BRANCH = "v2025.04-Beagle"
SRCREV = "ea2aeefcb2de36f8ca1b51d0580826d13c01f143"

BRANCH:beagley-ai = "v2025.04-rc3-BeagleY-AI"
BRANCH:beagley-ai-k3r5 = "v2025.04-rc3-BeagleY-AI"
SRCREV:beagley-ai = "cc376f9faef201670c7bb1734f42f5475c73f85d"
SRCREV:beagley-ai-k3r5 = "cc376f9faef201670c7bb1734f42f5475c73f85d"

SRC_URI:append:beagley-ai = " file://bootcmd-ti-mmc.cfg"

BRANCH:pocketbeagle2 = "v2025.04-pocketbeagle2"
BRANCH:pocketbeagle2-k3r5 = "v2025.04-pocketbeagle2"
SRCREV:pocketbeagle2 = "f7439516453b7728bda496ff6496ef6f1411aae3"
SRCREV:pocketbeagle2-k3r5 = "f7439516453b7728bda496ff6496ef6f1411aae3"

SRC_URI:append:pocketbeagle2 = " file://bootcmd-ti-mmc.cfg"
