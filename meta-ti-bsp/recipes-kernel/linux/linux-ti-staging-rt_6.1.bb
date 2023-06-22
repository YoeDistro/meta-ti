require linux-ti-staging_6.1.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-6.1:"

BRANCH = "ti-rt-linux-6.1.y"

SRCREV = "ed6083e7bf205689a2a009c9d2cd95f3ce55127b"
PV = "6.1.33+git${SRCPV}"
