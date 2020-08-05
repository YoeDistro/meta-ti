require linux-ti-staging_5.4.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-5.4:"

BRANCH = "ti-rt-linux-5.4.y"

SRCREV = "8b406259d03ff67bbe45cdceb304ab6be2d4fc68"
PV = "5.4.52+git${SRCPV}"
