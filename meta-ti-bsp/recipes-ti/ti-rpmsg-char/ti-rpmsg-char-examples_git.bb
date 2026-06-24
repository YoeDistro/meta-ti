SUMMARY = "TI RPMsg Char Sample Applications and Tests"

require ti-rpmsg-char.inc

SRC_URI += "file://0001-examples-Remove-use-of-includedir-and-libdir.patch"

DEPENDS = "ti-rpmsg-char"

inherit autotools pkgconfig

EXTRA_OEMAKE = "-C ${B}/examples"

PR = "r0"
