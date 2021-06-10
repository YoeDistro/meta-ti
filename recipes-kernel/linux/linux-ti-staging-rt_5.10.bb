require linux-ti-staging_5.10.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-5.10:"

BRANCH = "ti-rt-linux-5.10.y"

SRCREV = "06894f664e012a2df68e6fc28169d21042bb134a"
PV = "5.10.41+git${SRCPV}"
