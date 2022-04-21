require linux-ti-staging_5.10.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-5.10:"

BRANCH = "ti-rt-linux-5.10.y"

SRCREV = "84474b392bd8f1a8ebbabfa6ec685297fa3d51b7"
PV = "5.10.109+git${SRCPV}"
