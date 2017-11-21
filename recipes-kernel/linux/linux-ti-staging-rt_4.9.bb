require linux-ti-staging_4.9.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.9:"

BRANCH = "ti-rt-linux-4.9.y"

SRCREV = "3c21c435377084d4fc9abbba378cfc08beec0858"
