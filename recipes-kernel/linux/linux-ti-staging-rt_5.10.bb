require linux-ti-staging_5.10.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-5.10:"

BRANCH = "ti-rt-linux-5.10.y"

SRCREV = "bd4b7ce35aa987b5b6f80fc2efd2be9a4e707b84"
PV = "5.10.120+git${SRCPV}"
