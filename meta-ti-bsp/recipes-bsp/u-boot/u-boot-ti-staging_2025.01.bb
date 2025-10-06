require u-boot-ti.inc

PR = "r0"

BRANCH = "ti-u-boot-2025.01"

SRCREV_uboot = "934cf94788c505068308069903d42f6cdf30c0ae"

SRC_URI += "\
    file://0001-kconfig-Add-support-for-external-config-fragment-and.patch \
"
