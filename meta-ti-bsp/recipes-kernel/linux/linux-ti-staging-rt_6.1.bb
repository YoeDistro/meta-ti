require linux-ti-staging_6.1.bb

DEFAULT_PREFERENCE = "-1"

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-6.1:"

BRANCH = "ti-rt-linux-6.1.y"

SRCREV = "c9eb24939be548a72ddf19820805e48359be2ac5"
PV = "6.1.26+git${SRCPV}"
