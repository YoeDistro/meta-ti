require linux-ti-staging_4.1.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.1:"

BRANCH = "ti-rt-linux-4.1.y"

SRCREV = "71b02422332b1482fda520e92a3c9eb9c7a54ec1"
