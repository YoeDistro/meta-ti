require linux-ti-staging_6.1.bb

DEFAULT_PREFERENCE = "-1"

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-6.1:"

BRANCH = "ti-rt-linux-6.1.y"

SRCREV = "b7d4fb88264d2b094035871fa676592eb8e83b25"
PV = "6.1+git${SRCPV}"
