require u-boot-ti.inc

PR = "r25"

BRANCH = "ti-u-boot-2018.01"

SRCREV = "636878714c52ea3bab37bcdc5c638ea2fa10827c"

SRC_URI += "file://0001-Always-build-with-fno-PIE.patch"
