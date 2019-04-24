require linux-ti-staging_4.19.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.19:"

BRANCH = "ti-rt-linux-4.19.y"

SRCREV = "9df3c9d7cac643efcf1abd46711bb35ef0d16b71"
PV = "4.19.31+git${SRCPV}"
