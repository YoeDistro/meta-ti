require linux-ti-staging_6.1.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-6.1:"

BRANCH = "ti-rt-linux-6.1.y"

SRCREV = "72823691fbd87c95981bcd7062430ae58e0d2b20"
PV = "6.1.33+git${SRCPV}"
