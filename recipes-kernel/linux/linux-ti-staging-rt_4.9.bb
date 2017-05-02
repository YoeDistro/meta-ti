require linux-ti-staging_4.9.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.9:"

BRANCH = "ti-rt-linux-4.9.y"

SRCREV = "ca8f5854f1d211373e2bd80307215b086f18cbe1"
