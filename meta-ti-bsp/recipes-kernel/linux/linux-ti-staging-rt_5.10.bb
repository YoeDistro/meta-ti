require linux-ti-staging_5.10.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-5.10:"

BRANCH = "ti-rt-linux-5.10.y"

SRCREV = "1872082f52dc08d10c9db367dd3530fb9b4e5a9c"
PV = "5.10.120+git${SRCPV}"
