require u-boot-ti.inc

PR = "r1"

BRANCH = "ti-u-boot-2018.01"

SRCREV = "240ae44c5535bb7a2b6553813f5c42a4a1c5772a"

SRC_URI_append_ti33x = " file://0001-HACK-am335x-Work-around-file-expansion-macro.patch"
