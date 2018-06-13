require linux-ti-staging_4.14.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.14:"

BRANCH = "ti-rt-linux-4.14.y"

SRCREV = "2c7fc5f1d50fbfe18bebe22361fd4b522aeddeb7"
PV = "4.14.49+git${SRCPV}"
