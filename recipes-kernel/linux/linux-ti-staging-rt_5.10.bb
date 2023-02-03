require linux-ti-staging_5.10.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-5.10:"

BRANCH = "ti-rt-linux-5.10.y"

SRCREV = "d77d037d679dbfc9594856ccf2f08e7c808b9e93"
PV = "5.10.162+git${SRCPV}"
