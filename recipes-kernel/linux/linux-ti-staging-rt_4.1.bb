require linux-ti-staging_4.1.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.1:"

BRANCH = "ti-rt-linux-4.1.y"

SRCREV = "f80a400887b7c74204c0657d462f75ca1354cb1a"
