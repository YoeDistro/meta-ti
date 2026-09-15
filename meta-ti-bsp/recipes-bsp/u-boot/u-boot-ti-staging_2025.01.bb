require u-boot-ti.inc

PR = "r0"

BRANCH = "ti-u-boot-2025.01"

SRCREV_uboot = "4ca322ca563a21cccad8c9ba65e386b9fd34dd16"

SRC_URI += "file://0001-binman-migrate-form-pkg_resources-to-importlib.patch"
