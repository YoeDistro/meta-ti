require linux-ti-staging_4.14.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.14:"

BRANCH = "ti-rt-linux-4.14.y"

SRCREV = "89f3986aa0e6cc6a28c9f0afda67c9514d8ac40a"
PV = "4.14.67+git${SRCPV}"
