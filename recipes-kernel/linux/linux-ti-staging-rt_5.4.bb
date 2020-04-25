require linux-ti-staging_5.4.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-5.4:"

BRANCH = "ti-rt-linux-5.4.y"

SRCREV = "c19c1f9f817e15b923c18a22b218024788627692"
PV = "5.4.28+git${SRCPV}"
