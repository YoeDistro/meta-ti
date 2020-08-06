require linux-ti-staging_5.4.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-5.4:"

BRANCH = "ti-rt-linux-5.4.y"

SRCREV = "93189093447068135d91f93aa75ee1c1239438f1"
PV = "5.4.54+git${SRCPV}"
