require linux-ti-staging_6.1.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-6.1:"

BRANCH = "ti-rt-linux-6.1.y"

SRCREV = "ac4b77a441a3a0d745ea4c7b2112d99d5ed1628f"
PV = "6.1.26+git${SRCPV}"
