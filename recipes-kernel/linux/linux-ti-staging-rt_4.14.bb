require linux-ti-staging_4.14.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.14:"

BRANCH = "ti-rt-linux-4.14.y"

SRCREV = "4ec705911f68c4e11da2a4445b3202b4f2482a2e"
PV = "4.14.30+git${SRCPV}"
