require linux-ti-staging_5.10.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-5.10:"

BRANCH = "ti-rt-linux-5.10.y"

SRCREV = "2424cb2e4bb9ebd450a9e3008b7e465e78159b43"
PV = "5.10.162+git${SRCPV}"
