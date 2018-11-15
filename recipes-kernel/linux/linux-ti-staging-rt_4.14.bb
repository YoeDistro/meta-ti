require linux-ti-staging_4.14.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.14:"

BRANCH = "ti-rt-linux-4.14.y"

SRCREV = "aab33cfa0d3aed1fb463f6a6e62236db6b04fc5f"
PV = "4.14.79+git${SRCPV}"
