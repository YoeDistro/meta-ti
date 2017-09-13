require linux-ti-staging_4.9.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.9:"

BRANCH = "ti-rt-linux-4.9.y"

SRCREV = "47e434d98a40866672c439016dff01deacb5ed9c"
