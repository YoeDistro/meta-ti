require linux-ti-staging_5.4.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-5.4:"

BRANCH = "ti-rt-linux-5.4.y"

SRCREV = "a7f0924ffeb3e8dfaefece66aea3ef3d20f6b571"
PV = "5.4.20+git${SRCPV}"
