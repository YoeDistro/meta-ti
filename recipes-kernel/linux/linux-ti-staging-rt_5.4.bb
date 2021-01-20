require linux-ti-staging_5.4.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-5.4:"

BRANCH = "ti-rt-linux-5.4.y"

SRCREV = "e0c39cf61df45b3f69d7ad1afd919e104d39eba3"
PV = "5.4.87+git${SRCPV}"
