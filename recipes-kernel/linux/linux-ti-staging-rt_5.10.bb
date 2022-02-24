require linux-ti-staging_5.10.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-5.10:"

BRANCH = "ti-rt-linux-5.10.y"

SRCREV = "8844ac0db67cf31b83b1b1b125baf1e637ef38d0"
PV = "5.10.100+git${SRCPV}"
