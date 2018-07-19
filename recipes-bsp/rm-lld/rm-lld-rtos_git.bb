inherit ti-pdk

COMPATIBLE_MACHINE_append = "|c66x"

require rm-lld.inc

PR = "${INC_PR}.2"

PACKAGE_ARCH = "${MACHINE_ARCH}"

# HTML doc link params
PDK_COMP_LINK_TEXT = "RM LLD"

