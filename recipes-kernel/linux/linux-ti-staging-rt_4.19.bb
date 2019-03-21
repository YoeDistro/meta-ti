require linux-ti-staging_4.19.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.19:"

BRANCH = "ti-rt-linux-4.19.y"

SRCREV = "24b7d60eb75b1193fed97f0a275ff47afddb6dc3"
PV = "4.19.25+git${SRCPV}"
