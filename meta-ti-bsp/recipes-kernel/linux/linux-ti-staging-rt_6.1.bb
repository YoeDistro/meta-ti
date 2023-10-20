require linux-ti-staging_6.1.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-6.1:"

BRANCH = "ti-rt-linux-6.1.y"

SRCREV = "ccf548983bd6630d4a1a5b60d94d25d6387b73d2"
PV = "6.1.46+git${SRCPV}"
