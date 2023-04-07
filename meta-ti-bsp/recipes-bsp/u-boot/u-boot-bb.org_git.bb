require u-boot-ti.inc

SUMMARY = "BeagleBoard.org U-Boot"

COMPATIBLE_MACHINE = "beagle.*"

LIC_FILES_CHKSUM = "file://Licenses/README;md5=5a7450c57ffe5ae63fd732446b988025"

PV = "2021.01"

UBOOT_GIT_URI = "git://github.com/beagleboard/u-boot.git"
UBOOT_GIT_PROTOCOL = "https"
BRANCH = "v2021.01-ti-08.05.00.005-SDK-8.5"
SRCREV = "46ff4982b41067e5c93369bddd49b1541856d80b"

BRANCH:beaglebone-ai64 = "v2021.01-ti-08.05.00.001"
BRANCH:beaglebone-ai64-k3r5 = "v2021.01-ti-08.05.00.001"
SRCREV:beaglebone-ai64 = "ea96725b5156135d5875415f75d2188f6f56622a"
SRCREV:beaglebone-ai64-k3r5 = "ea96725b5156135d5875415f75d2188f6f56622a"

BRANCH:beagleplay = "v2021.01-ti-BeaglePlay-Release"
BRANCH:beagleplay-k3r5 = "v2021.01-ti-BeaglePlay-Release"
SRCREV:beagleplay = "f036fbdc25941d7585182d2552c767edb9b04114"
SRCREV:beagleplay-k3r5 = "f036fbdc25941d7585182d2552c767edb9b04114"
