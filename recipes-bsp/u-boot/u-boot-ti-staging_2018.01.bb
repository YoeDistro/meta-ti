require u-boot-ti.inc

PR = "r30"

BRANCH = "ti-u-boot-2018.01"

SRCREV = "313dcd69c2b32648266f91bcf223f9e539bc4201"

SRC_URI += "file://0001-Always-build-with-fno-PIE.patch"
