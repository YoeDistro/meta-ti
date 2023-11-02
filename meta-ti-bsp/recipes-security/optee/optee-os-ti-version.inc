PV = "4.0.0+git${SRCPV}"
SRCREV = "2a5b1d1232f582056184367fb58a425ac7478ec6"

# Fixes for pointing to 4.0.0 before upstream meta-arm
FILESEXTRAPATHS:prepend := "${THISDIR}/optee-os-4.0.0:"
SRC_URI:remove = "file://0002-core-Define-section-attributes-for-clang.patch"
SRC_URI:append = " file://0002ti-core-Define-section-attributes-for-clang.patch"
