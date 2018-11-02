require u-boot-ti.inc

PR = "r26"

BRANCH = "ti-u-boot-2018.01"

SRCREV = "2ac9e323adb9df043e02cf660a797d56b09bbee4"

SRC_URI += "file://0001-Always-build-with-fno-PIE.patch"
