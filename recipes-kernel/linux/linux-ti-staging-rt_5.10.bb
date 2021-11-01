require linux-ti-staging_5.10.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-5.10:"

BRANCH = "ti-rt-linux-5.10.y"

SRCREV = "6f4d81d846c7c548ccc05498c4ad824f31dd6fc0"
PV = "5.10.65+git${SRCPV}"
