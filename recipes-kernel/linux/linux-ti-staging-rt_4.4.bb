require linux-ti-staging_4.4.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.4:"

BRANCH = "ti-rt-linux-4.4.y"

SRCREV = "f78a6f41de4e9319739b184307b242ee96602762"
