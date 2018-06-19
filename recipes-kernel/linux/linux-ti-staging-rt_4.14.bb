require linux-ti-staging_4.14.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.14:"

BRANCH = "ti-rt-linux-4.14.y"

SRCREV = "0027520b443976042f208d318bb7266683f38b49"
PV = "4.14.50+git${SRCPV}"
