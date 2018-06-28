require linux-ti-staging_4.14.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.14:"

BRANCH = "ti-rt-linux-4.14.y"

SRCREV = "3e637f01a8c09912cc459b4d2a12f8f97b36d1fc"
PV = "4.14.50+git${SRCPV}"
