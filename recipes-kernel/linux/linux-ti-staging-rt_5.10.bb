require linux-ti-staging_5.10.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-5.10:"

BRANCH = "ti-rt-linux-5.10.y"

SRCREV = "d9ee8a25c2eea0d2aff22f36ced2513538fbd1a2"
PV = "5.10.109+git${SRCPV}"
