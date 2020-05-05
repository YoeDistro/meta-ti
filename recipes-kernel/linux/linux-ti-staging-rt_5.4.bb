require linux-ti-staging_5.4.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-5.4:"

BRANCH = "ti-rt-linux-5.4.y"

SRCREV = "f9105a2c9d7c0fd514250ee3cb9397a3ff87d23e"
PV = "5.4.34+git${SRCPV}"
