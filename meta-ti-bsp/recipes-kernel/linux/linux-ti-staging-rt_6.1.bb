require linux-ti-staging_6.1.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-6.1:"

BRANCH = "ti-rt-linux-6.1.y"

SRCREV = "eda9974e8513cdd2c5cc20c3c6b851f9977acd4d"
PV = "6.1.46+git${SRCPV}"
