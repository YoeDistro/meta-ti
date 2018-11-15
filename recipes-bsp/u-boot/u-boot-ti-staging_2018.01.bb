require u-boot-ti.inc

PR = "r28"

BRANCH = "ti-u-boot-2018.01"

SRCREV = "b1db1fb0d39fa706f0ad8e36f301aba429475bed"

SRC_URI += "file://0001-Always-build-with-fno-PIE.patch"
