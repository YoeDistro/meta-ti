require linux-ti-staging_4.19.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.19:"

BRANCH = "ti-rt-linux-4.19.y"

SRCREV = "7e052f5026f26c6e7ffe1f0a7961dd628b73e3c1"
PV = "4.19.73+git${SRCPV}"
