require u-boot-ti.inc

SUMMARY = "BeagleBoard.org U-Boot"

COMPATIBLE_MACHINE = "beagle.*"

PV = "2023.04"

UBOOT_GIT_URI = "git://github.com/beagleboard/u-boot.git"
UBOOT_GIT_PROTOCOL = "https"
BRANCH = "v2023.04-ti-09.00.00.011"
SRCREV = "4b8f8be8870f03d92a2b01d0d30ab912118adc82"

BRANCH:beagleplay = "v2023.04-ti-09.00.00.011-BeaglePlay"
BRANCH:beagleplay-k3r5 = "v2023.04-ti-09.00.00.011-BeaglePlay"
SRCREV:beagleplay = "10afea462ea1113701f6f0f8853fdff985aa275d"
SRCREV:beagleplay-k3r5 = "10afea462ea1113701f6f0f8853fdff985aa275d"
