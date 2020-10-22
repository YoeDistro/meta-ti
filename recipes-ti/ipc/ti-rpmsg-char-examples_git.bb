SUMMARY = "TI RPMsg Char Sample Applications and Tests"

require ti-rpmsg-char.inc

DEPENDS = "ti-rpmsg-char"

inherit autotools pkgconfig

EXTRA_OEMAKE = "-C ${B}/examples"

PR = "r0"
