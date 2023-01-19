require linux-ti-staging_5.10.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-5.10:"

BRANCH = "ti-rt-linux-5.10.y"

SRCREV = "81b9bbe05bd82c315c8ddc77974b1b3031d03bfd"
PV = "5.10.158+git${SRCPV}"
