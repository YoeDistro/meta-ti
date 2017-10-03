require linux-ti-staging_4.9.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.9:"

BRANCH = "ti-rt-linux-4.9.y"

SRCREV = "3e12ba947dd5acc384a07de643ad1a14c6a5161c"
