require linux-ti-staging_5.10.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-5.10:"

BRANCH = "ti-rt-linux-5.10.y"

SRCREV = "979ff550e329cac9253d9c37994d73ea12f043de"
PV = "5.10.145+git${SRCPV}"
