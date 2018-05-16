require linux-ti-staging_4.14.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.14:"

BRANCH = "ti-rt-linux-4.14.y"

SRCREV = "8dac75629c836fb3d75d51fbd2500df9b0b7edb9"
PV = "4.14.40+git${SRCPV}"
