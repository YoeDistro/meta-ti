require linux-ti-staging_6.1.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-6.1:"

BRANCH = "ti-rt-linux-6.1.y"

SRCREV = "833521d9b354beb95bac8b37bfc211495d73d36c"

include ${@ 'recipes-kernel/linux/ti-extras-rt.inc' if d.getVar('TI_EXTRAS') else ''}

PV = "6.1.46+git${SRCPV}"
