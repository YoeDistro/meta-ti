require linux-ti-staging_5.10.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-5.10:"

BRANCH = "ti-rt-linux-5.10.y"

SRCREV = "ea11ea50a9196c9aeb880e0e4ddd5aa3ba9e149c"
PV = "5.10.153+git${SRCPV}"
