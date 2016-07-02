require linux-ti-staging_4.4.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.4:"

BRANCH = "ti-rt-linux-4.4.y"

SRCREV = "52be87b097a0c15af2a6d30ff7743796a07c9da2"
