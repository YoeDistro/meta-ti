SUMMARY = "TI Usage & Load Monitor Implementation"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://../debian/copyright;md5=309825aa8f5edfcf2c44912ac094b979"

inherit features_check

REQUIRED_MACHINE_FEATURES = "dsp"

DEPENDS = "ti-cgt6x-native"
PR = "${INC_PR}.0"

S:append = "/dsptop/ulm"

COMPATIBLE_MACHINE = "am57xx"

EXTRA_OEMAKE = "release DEVICE=DRA7xx CROSS_COMPILE=${TARGET_PREFIX}"

do_compile() {
    oe_runmake arm XPORT_ONLY CC="${CC}"
    oe_runmake dsp C6X_C_DIR=${STAGING_DIR_NATIVE}/usr/share/ti/cgt-c6x/include
}

do_install() {
    install -d ${D}${includedir}
    install -d ${D}${libdir}
    install -d ${D}${datadir}/ti/ulm
    cp -f tiulm.h ${D}${includedir}
    cp -f release/libtiulm.a ${D}${libdir}
    cp -f tiulm.h ${D}${datadir}/ti/ulm
    cp -f release/libtiulm.ae66 ${D}${datadir}/ti/ulm
}

FILES:${PN}-dev += "\
    ${datadir}/ti/ulm \
"

include dsptop.inc

ALLOW_EMPTY:${PN} = "1"

PARALLEL_MAKE = ""

# Disable the "buildpaths" check while we figure out how we are
# going to address this issue.
#
# The ti-cgt6x compiler is a custom TI compiler for the TI C6000
# Digital Signal Processor(DSP) platform.  It does not currently
# support reproducible builds and is provided via a binary blob
# download that we cannot patch in the recipe to address the
# issue.
INSANE_SKIP:${PN}-dev += "buildpaths"
