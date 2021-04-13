require linux-ti-staging_5.10.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-5.10:"

BRANCH = "ti-rt-linux-5.10.y"

SRCREV = "85acf0d261f2dfd6c0bc5dcb7fda42771656701d"
PV = "5.10.21+git${SRCPV}"
