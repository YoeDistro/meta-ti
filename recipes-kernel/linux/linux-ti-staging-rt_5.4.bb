require linux-ti-staging_5.4.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-5.4:"

BRANCH = "ti-rt-linux-5.4.y"

SRCREV = "ff85c3d7a19f2d67e89da0cf44c7a7820f37f498"
PV = "5.4.93+git${SRCPV}"
