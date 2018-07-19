inherit ti-pdk

require recipes-bsp/mmap-lld/mmap-lld.inc

PR = "${INC_PR}.0"

PACKAGE_ARCH = "${MACHINE_ARCH}"

# HTML doc link params
PDK_COMP_LINK_TEXT = "MMAP LLD"
