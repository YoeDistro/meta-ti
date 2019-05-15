require linux-ti-staging_4.19.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.19:"

BRANCH = "ti-rt-linux-4.19.y"

SRCREV = "b8935c3f161b90a15cec065066d710b216ae5c97"
PV = "4.19.38+git${SRCPV}"
