require linux-ti-staging_5.10.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-5.10:"

BRANCH = "ti-rt-linux-5.10.y"

SRCREV = "a18c1a107ff469001d4f3d20c8e34146fb1f58c4"
PV = "5.10.158+git${SRCPV}"
