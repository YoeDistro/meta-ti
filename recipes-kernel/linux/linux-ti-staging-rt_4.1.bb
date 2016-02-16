require linux-ti-staging_4.1.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.1:"

BRANCH = "ti-rt-linux-4.1.y"

SRCREV = "fe2b227e0288c6e2dd27f8f382a831c180ca2d4f"
