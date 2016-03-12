require linux-ti-staging_4.1.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.1:"

BRANCH = "ti-rt-linux-4.1.y"

SRCREV = "6d0ade6343f01482b46210e9e474f2cac32ff474"
