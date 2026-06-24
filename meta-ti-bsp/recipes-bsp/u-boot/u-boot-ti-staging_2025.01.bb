require u-boot-ti.inc

PR = "r0"

BRANCH = "ti-u-boot-2025.01"

SRCREV_uboot = "1612e4d1deb11d49304c715576854b28cc10e34d"

SRC_URI += "file://0001-binman-migrate-form-pkg_resources-to-importlib.patch"
