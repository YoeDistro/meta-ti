require linux-ti-staging_6.1.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-6.1:"

BRANCH = "ti-rt-linux-6.1.y"

SRCREV = "75de1d1b3fe8fbd77945dca6f23b1fc3ea3ba4ec"
PV = "6.1.26+git${SRCPV}"
