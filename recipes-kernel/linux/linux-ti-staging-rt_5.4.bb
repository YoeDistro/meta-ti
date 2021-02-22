require linux-ti-staging_5.4.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-5.4:"

BRANCH = "ti-rt-linux-5.4.y"

SRCREV = "f88a6c0071660458ed8985500ed5c4b5c8a7f372"
PV = "5.4.93+git${SRCPV}"
