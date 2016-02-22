require linux-ti-staging_4.1.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.1:"

BRANCH = "ti-rt-linux-4.1.y"

SRCREV = "a52a12de4129f0668bb2c93a8a0d701a7b72ca60"
