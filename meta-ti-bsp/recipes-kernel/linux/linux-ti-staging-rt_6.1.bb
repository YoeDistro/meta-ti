require linux-ti-staging_6.1.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-6.1:"

BRANCH = "ti-rt-linux-6.1.y"

SRCREV = "443078b4c6f63e8c22eac8206be47f45296cfb0c"
PV = "6.1.33+git${SRCPV}"
