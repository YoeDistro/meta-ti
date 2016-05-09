require linux-ti-staging_4.4.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.4:"

BRANCH = "ti-rt-linux-4.4.y"

SRCREV = "0997a7f0b943274b71db74f01018c7b7e746eec2"
