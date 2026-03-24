require u-boot-ti.inc

PR = "r0"

BRANCH = "ti-u-boot-2025.01"

SRCREV_uboot = "7b9dedb046eb6a720997f61582c7b13da1b5b9f0"

SRC_URI += "file://0001-binman-migrate-form-pkg_resources-to-importlib.patch"
