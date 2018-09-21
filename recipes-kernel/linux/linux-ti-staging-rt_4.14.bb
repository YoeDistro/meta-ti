require linux-ti-staging_4.14.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.14:"

BRANCH = "ti-rt-linux-4.14.y"

SRCREV = "ae5042b9a06e578aa50c46386f30449c3f4e7171"
PV = "4.14.69+git${SRCPV}"
