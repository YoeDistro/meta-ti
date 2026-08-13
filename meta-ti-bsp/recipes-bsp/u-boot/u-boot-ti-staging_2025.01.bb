require u-boot-ti.inc

PR = "r0"

BRANCH = "ti-u-boot-2025.01"

SRCREV_uboot = "13f4e50ff203b9581dec599a620b57bb9ff1ed9d"

SRC_URI += "file://0001-binman-migrate-form-pkg_resources-to-importlib.patch"
