inherit ti-pdk

COMPATIBLE_MACHINE_append = "|c66x"

require rm-lld.inc

PR = "${INC_PR}.1"

PACKAGE_ARCH = "${MACHINE_ARCH}"
