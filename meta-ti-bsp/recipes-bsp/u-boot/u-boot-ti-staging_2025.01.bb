require u-boot-ti.inc

PR = "r0"

BRANCH = "ti-u-boot-2025.01"

SRCREV_uboot = "19795f63be7ee27e38b6e800ff6c88a2feaae13f"

SRC_URI += "file://0001-binman-migrate-form-pkg_resources-to-importlib.patch"
