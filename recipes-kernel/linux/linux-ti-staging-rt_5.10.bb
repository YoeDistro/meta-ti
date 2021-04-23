require linux-ti-staging_5.10.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-5.10:"

BRANCH = "ti-rt-linux-5.10.y"

SRCREV = "79bfe5400b3fe9350cca6a009d8fdafcfc59879f"
PV = "5.10.21+git${SRCPV}"
