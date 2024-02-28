PV = "4.1.0+git"
SRCREV = "012cdca49db398693903e05c42a254a3a0c0d8f2"

# Fixes for pointing to 4.0.0 before upstream meta-arm
FILESEXTRAPATHS:prepend := "${THISDIR}/optee-os-4.0.0:"
SRC_URI:remove = "file://0002-core-Define-section-attributes-for-clang.patch"
SRC_URI:append = " file://0002ti-core-Define-section-attributes-for-clang.patch"
