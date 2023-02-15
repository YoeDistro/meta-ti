require linux-ti-staging_5.10.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-5.10:"

BRANCH = "ti-rt-linux-5.10.y"

SRCREV = "429ec5d1d49d58151ef89999ee2ad510b5820957"
PV = "5.10.162+git${SRCPV}"
