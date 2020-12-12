require linux-ti-staging_5.4.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-5.4:"

BRANCH = "ti-rt-linux-5.4.y"

SRCREV = "058c8ee4e4ae3ae72ca01b5b4b2aea8213561ab9"
PV = "5.4.82+git${SRCPV}"
