require linux-ti-staging_4.19.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.19:"

BRANCH = "ti-rt-linux-4.19.y"

SRCREV = "b94ae17a71eace50d505c47f4f1f039625a9c729"
PV = "4.19.73+git${SRCPV}"
