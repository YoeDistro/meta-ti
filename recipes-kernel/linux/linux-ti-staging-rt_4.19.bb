require linux-ti-staging_4.19.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.19:"

BRANCH = "ti-rt-linux-4.19.y"

SRCREV = "5785287a3edc2b91165f5f7475e5ec696d338fc7"
PV = "4.19.79+git${SRCPV}"
