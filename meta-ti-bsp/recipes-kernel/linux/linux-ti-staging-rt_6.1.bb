require linux-ti-staging_6.1.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-6.1:"

BRANCH = "ti-rt-linux-6.1.y"

SRCREV = "6c7b09058d06625c8386decb5573da65e3c4ccfc"
PV = "6.1.46+git${SRCPV}"
