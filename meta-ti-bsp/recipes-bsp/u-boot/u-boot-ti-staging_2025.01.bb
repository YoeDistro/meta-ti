require u-boot-ti.inc

PR = "r0"

BRANCH = "ti-u-boot-2025.01"

SRCREV_uboot = "87519a151561393ce1244ba53401196b011b7a39"

SRC_URI += "\
    file://0001-kconfig-Add-support-for-external-config-fragment-and.patch \
"
