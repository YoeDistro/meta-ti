require u-boot-ti.inc

PR = "r0"

BRANCH = "ti-u-boot-2025.01"

SRCREV_uboot = "aa613c2e83299906b121fcf0d6a5824f1d1c3ff9"

SRC_URI += "\
    file://0001-kconfig-Add-support-for-external-config-fragment-and.patch \
"
