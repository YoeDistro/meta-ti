require linux-ti-staging_5.10.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-5.10:"

BRANCH = "ti-rt-linux-5.10.y"

SRCREV = "29dbc132ebfcdca4022663d80dbc6e7162c53ce5"
PV = "5.10.153+git${SRCPV}"
