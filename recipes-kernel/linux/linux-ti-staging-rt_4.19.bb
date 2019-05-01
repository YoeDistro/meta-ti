require linux-ti-staging_4.19.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.19:"

BRANCH = "ti-rt-linux-4.19.y"

SRCREV = "a822a65ebd68ac674d170148e2354ed5f0b3dcc4"
PV = "4.19.37+git${SRCPV}"
