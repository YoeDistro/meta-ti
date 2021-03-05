require linux-ti-staging_5.4.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-5.4:"

BRANCH = "ti-rt-linux-5.4.y"

SRCREV = "6cd92cf89235d5ddb3c0104d02c0f3db48efdba4"
PV = "5.4.93+git${SRCPV}"
