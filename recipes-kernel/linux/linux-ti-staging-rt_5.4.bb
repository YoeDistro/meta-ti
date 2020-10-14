require linux-ti-staging_5.4.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-5.4:"

BRANCH = "ti-rt-linux-5.4.y"

SRCREV = "a5dca123aec8168af717956d3b1896a33989313c"
PV = "5.4.70+git${SRCPV}"
