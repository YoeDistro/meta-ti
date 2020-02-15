require linux-ti-staging_5.4.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-5.4:"

BRANCH = "ti-rt-linux-5.4.y"

SRCREV = "d695269f31dedd0e1cd07412e7ccac61cfd8fc45"
PV = "5.4.17+git${SRCPV}"
