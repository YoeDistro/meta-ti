require linux-ti-staging_4.19.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.19:"

BRANCH = "ti-rt-linux-4.19.y"

SRCREV = "4a1d8698636cf4c7b4bcac5ff2e13ff0df8b74e0"
PV = "4.19.82+git${SRCPV}"
