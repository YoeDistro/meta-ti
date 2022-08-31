require linux-ti-staging_5.10.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-5.10:"

BRANCH = "ti-rt-linux-5.10.y"

SRCREV = "c40ea4ad4faa2ed1ab94eb7fc9e588b19725090f"
PV = "5.10.131+git${SRCPV}"
