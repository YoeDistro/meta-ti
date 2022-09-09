require linux-ti-staging_5.10.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-5.10:"

BRANCH = "ti-rt-linux-5.10.y"

SRCREV = "a3d6e64d5c28ce95a6c381cf76efcfbeecff3154"
PV = "5.10.140+git${SRCPV}"
