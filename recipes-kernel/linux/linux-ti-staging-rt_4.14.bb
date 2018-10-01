require linux-ti-staging_4.14.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.14:"

BRANCH = "ti-rt-linux-4.14.y"

SRCREV = "a6b7e0608adb4e4b2fab789bcf85cedfb7129bc6"
PV = "4.14.71+git${SRCPV}"
