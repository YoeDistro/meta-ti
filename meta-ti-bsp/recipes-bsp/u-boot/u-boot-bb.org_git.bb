require u-boot-ti.inc

SUMMARY = "BeagleBoard.org U-Boot"

COMPATIBLE_MACHINE = "beagle.*"

PV = "2023.04"

UBOOT_GIT_URI = "git://github.com/beagleboard/u-boot.git"
UBOOT_GIT_PROTOCOL = "https"
BRANCH = "v2023.04-ti-09.01.00.008"
SRCREV = "b0d717b732ee28e446baf94522b3491e590f7fbb"

BRANCH:beagleplay = "v2023.04-ti-09.01.00.008-BeaglePlay"
BRANCH:beagleplay-k3r5 = "v2023.04-ti-09.01.00.008-BeaglePlay"
SRCREV:beagleplay = "43791d945f4e5c25bcc19b9c778e8f9d194dc16e"
SRCREV:beagleplay-k3r5 = "43791d945f4e5c25bcc19b9c778e8f9d194dc16e"
