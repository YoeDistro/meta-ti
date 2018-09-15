require linux-ti-staging_4.14.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.14:"

BRANCH = "ti-rt-linux-4.14.y"

SRCREV = "ba07e295a734f2d1faaff6105e5e7d26b09d7a70"
PV = "4.14.69+git${SRCPV}"
