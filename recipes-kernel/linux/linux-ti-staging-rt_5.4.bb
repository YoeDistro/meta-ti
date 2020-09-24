require linux-ti-staging_5.4.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-5.4:"

BRANCH = "ti-rt-linux-5.4.y"

SRCREV = "fe1ab63ac9248a6fecb8572a0ad5dc51a79ac280"
PV = "5.4.66+git${SRCPV}"
