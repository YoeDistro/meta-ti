require linux-ti-staging_6.1.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-6.1:"

BRANCH = "ti-rt-linux-6.1.y"

SRCREV = "dd944f14304d2ce6d26c9cdc3d2d2d6d373a322c"
PV = "6.1.46+git${SRCPV}"
