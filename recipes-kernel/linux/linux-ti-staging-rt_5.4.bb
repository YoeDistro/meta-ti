require linux-ti-staging_5.4.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-5.4:"

BRANCH = "ti-rt-linux-5.4.y"

SRCREV = "cfa321c18ddfc08dc4f4417c93fe607dadd9cdee"
PV = "5.4.84+git${SRCPV}"
