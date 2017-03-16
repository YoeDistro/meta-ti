require linux-ti-staging_4.4.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.4:"

BRANCH = "ti-rt-linux-4.4.y"

SRCREV = "00eb0a0737110139728384e2be21656f9669f8a8"
