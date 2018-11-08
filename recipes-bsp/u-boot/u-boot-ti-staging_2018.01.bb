require u-boot-ti.inc

PR = "r27"

BRANCH = "ti-u-boot-2018.01"

SRCREV = "9e1b118712213d2cab15280b5c6776bef35d4d59"

SRC_URI += "file://0001-Always-build-with-fno-PIE.patch"
