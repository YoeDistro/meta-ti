require linux-ti-staging_5.4.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-5.4:"

BRANCH = "ti-rt-linux-5.4.y"

SRCREV = "cfa5bbe5258d318b6fcf9743ae9002ec0a5f158a"
PV = "5.4.93+git${SRCPV}"
