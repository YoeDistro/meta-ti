require linux-ti-staging_4.19.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.19:"

BRANCH = "ti-rt-linux-4.19.y"

SRCREV = "f9b2abe6fa91456aa50a3ee0410db928455f98e0"
PV = "4.19.82+git${SRCPV}"
