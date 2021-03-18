require linux-ti-staging_5.4.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-5.4:"

BRANCH = "ti-rt-linux-5.4.y"

SRCREV = "5b5ca4d6ecedcb05721f0d5a7d753dd01c37b1f8"
PV = "5.4.93+git${SRCPV}"
