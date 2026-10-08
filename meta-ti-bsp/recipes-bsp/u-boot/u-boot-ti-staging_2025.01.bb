require u-boot-ti.inc

PR = "r0"

BRANCH = "ti-u-boot-2025.01"

SRCREV_uboot = "70800b68682ddc7a9e7e6695712a94fc9f62ed37"

SRC_URI += "file://0001-binman-migrate-form-pkg_resources-to-importlib.patch"
