require linux-ti-staging_5.4.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-5.4:"

BRANCH = "ti-rt-linux-5.4.y"

SRCREV = "e62f2659b5254a7fcdaad8f5a7705040ccaadeae"
PV = "5.4.34+git${SRCPV}"
