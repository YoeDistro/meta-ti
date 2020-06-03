require linux-ti-staging_5.4.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-5.4:"

BRANCH = "ti-rt-linux-5.4.y"

SRCREV = "17e2c5c658e9bcdf7566c9e7f70b1f0a8c9f7036"
PV = "5.4.43+git${SRCPV}"
