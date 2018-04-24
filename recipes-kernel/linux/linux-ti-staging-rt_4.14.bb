require linux-ti-staging_4.14.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.14:"

BRANCH = "ti-rt-linux-4.14.y"

SRCREV = "e5e55e02cf303fe8428e324ad0e17e4ad7e471cb"
PV = "4.14.35+git${SRCPV}"
