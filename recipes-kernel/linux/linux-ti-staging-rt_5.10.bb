require linux-ti-staging_5.10.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-5.10:"

BRANCH = "ti-rt-linux-5.10.y"

SRCREV = "afe9ba17bb94cd226f9e5dc3685e4fe32be28c5c"
PV = "5.10.35+git${SRCPV}"
