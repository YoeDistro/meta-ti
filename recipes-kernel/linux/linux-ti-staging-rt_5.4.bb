require linux-ti-staging_5.4.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-5.4:"

BRANCH = "ti-rt-linux-5.4.y"

SRCREV = "92c1547f2d83a4a75a1181a88631ef2d64f1e0dd"
PV = "5.4.52+git${SRCPV}"
