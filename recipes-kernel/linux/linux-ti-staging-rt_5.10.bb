require linux-ti-staging_5.10.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-5.10:"

BRANCH = "ti-rt-linux-5.10.y"

SRCREV = "190fd51ef1b5f1757b87c4c7dfdc33cecd210a54"
PV = "5.10.153+git${SRCPV}"
