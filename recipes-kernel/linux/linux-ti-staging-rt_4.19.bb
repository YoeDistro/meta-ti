require linux-ti-staging_4.19.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.19:"

BRANCH = "ti-rt-linux-4.19.y"

SRCREV = "bec45aa9b46eaf73cfdfbd4a95b3d6c8c3d3dac6"
PV = "4.19.50+git${SRCPV}"
