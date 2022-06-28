require linux-ti-staging_5.10.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-5.10:"

BRANCH = "ti-rt-linux-5.10.y"

SRCREV = "7e2ed4859e948ee827abe3c8b59dfa4e046e51ce"
PV = "5.10.120+git${SRCPV}"
