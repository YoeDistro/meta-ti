require linux-ti-staging_4.4.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.4:"

BRANCH = "ti-rt-linux-4.4.y"

SRCREV = "a316253be548aa1fbad2ede3a1c8b62ac2f142e5"
