require linux-ti-staging_6.1.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-6.1:"

BRANCH = "ti-rt-linux-6.1.y"

SRCREV = "9777e0138b484ea15cad0dc4c5d1ce928b79fb7c"
PV = "6.1.33+git${SRCPV}"
