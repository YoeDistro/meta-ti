require u-boot-ti.inc

PR = "r0"

BRANCH = "ti-u-boot-2025.01"

SRCREV_uboot = "f6338e22d2ac0777a68fb0f295f4cf1e3e63f659"

SRC_URI += "file://0001-binman-migrate-form-pkg_resources-to-importlib.patch"
