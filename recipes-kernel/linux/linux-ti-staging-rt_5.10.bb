require linux-ti-staging_5.10.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-5.10:"

BRANCH = "ti-rt-linux-5.10.y"

SRCREV = "432fb3ca999d3fc5cd43168bd5d08ab4270545b9"
PV = "5.10.153+git${SRCPV}"
