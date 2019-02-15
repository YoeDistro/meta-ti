require linux-ti-staging_4.19.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.19:"

BRANCH = "ti-rt-linux-4.19.y"

SRCREV = "dc9886fc8d12a9721b9ba198ee10d2c4720fba5e"
PV = "4.19.15+git${SRCPV}"
