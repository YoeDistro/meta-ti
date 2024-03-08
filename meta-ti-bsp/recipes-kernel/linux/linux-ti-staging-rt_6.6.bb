require linux-ti-staging_6.6.bb

DEFAULT_PREFERENCE = "-1"

KERNEL_LOCALVERSION:append = "-rt"

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-6.6:"

BRANCH = "ti-rt-linux-6.6.y"

SRCREV = "7a7ad8a0b720d8c9d7bf85b897814cf8500a4c50"

include ${@ 'recipes-kernel/linux/ti-extras-rt.inc' if d.getVar('TI_EXTRAS') else ''}

PV = "6.6.20+git${SRCPV}"
