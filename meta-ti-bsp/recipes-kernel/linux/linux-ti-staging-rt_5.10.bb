require linux-ti-staging_5.10.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-5.10:"

BRANCH = "ti-rt-linux-5.10.y"

SRCREV = "70d8aea23ac37d6a72bfd18fd8661ca6f1136c0a"
PV = "5.10.120+git${SRCPV}"
