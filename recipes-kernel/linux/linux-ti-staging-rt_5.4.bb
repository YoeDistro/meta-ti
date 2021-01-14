require linux-ti-staging_5.4.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-5.4:"

BRANCH = "ti-rt-linux-5.4.y"

SRCREV = "7e059d122f1e1cdcf2452f69fb6fd42a51ba899b"
PV = "5.4.87+git${SRCPV}"
