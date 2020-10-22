require linux-ti-staging_5.4.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-5.4:"

BRANCH = "ti-rt-linux-5.4.y"

SRCREV = "0343656e0feca050ec3f6a0a47fc65018d5a7fd1"
PV = "5.4.70+git${SRCPV}"
