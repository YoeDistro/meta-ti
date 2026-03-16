require u-boot-ti.inc

PR = "r0"

BRANCH = "ti-u-boot-2025.01"

SRCREV_uboot = "ef2eb76b650415637bd93b0eddfb1e31489117f9"

SRC_URI += "file://0001-binman-migrate-form-pkg_resources-to-importlib.patch"
