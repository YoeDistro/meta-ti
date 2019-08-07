require linux-ti-staging_4.19.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.19:"

BRANCH = "ti-rt-linux-4.19.y"

SRCREV = "29e336b7bd52d27d6143ff3b1310a93fc1ae0ca6"
PV = "4.19.59+git${SRCPV}"
