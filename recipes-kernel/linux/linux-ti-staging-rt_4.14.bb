require linux-ti-staging_4.14.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.14:"

BRANCH = "ti-rt-linux-4.14.y"

SRCREV = "cb407c0328dd3e46b8df9e1733f7d55c971a4ebe"
PV = "4.14.53+git${SRCPV}"
