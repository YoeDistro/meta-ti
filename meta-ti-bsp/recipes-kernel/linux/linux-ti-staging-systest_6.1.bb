require linux-ti-staging_6.1.bb

DEFAULT_PREFERENCE = "-1"

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-6.1:"
