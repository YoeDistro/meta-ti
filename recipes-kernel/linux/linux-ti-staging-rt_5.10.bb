require linux-ti-staging_5.10.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-5.10:"

BRANCH = "ti-rt-linux-5.10.y"

SRCREV = "0df7cc0df559a07abff8ef6bc17f3a04d5af1aa1"
PV = "5.10.153+git${SRCPV}"
