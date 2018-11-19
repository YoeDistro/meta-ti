require u-boot-ti.inc

PR = "r29"

BRANCH = "ti-u-boot-2018.01"

SRCREV = "eac41e26ac56fcabf9826b21377931c7b8e646f2"

SRC_URI += "file://0001-Always-build-with-fno-PIE.patch"
