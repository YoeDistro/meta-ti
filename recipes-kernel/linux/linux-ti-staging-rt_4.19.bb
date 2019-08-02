require linux-ti-staging_4.19.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.19:"

BRANCH = "ti-rt-linux-4.19.y"

SRCREV = "57d7f0b3d0bcd0b9a2f579010b6ece87dc43cd8b"
PV = "4.19.59+git${SRCPV}"
