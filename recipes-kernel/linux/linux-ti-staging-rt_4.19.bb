require linux-ti-staging_4.19.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.19:"

BRANCH = "ti-rt-linux-4.19.y"

SRCREV = "1aeb09dbca76a837ae2b721c6dd466aaa3b5f646"
PV = "4.19.38+git${SRCPV}"
