require linux-ti-staging_6.1.bb

KERNEL_LOCALVERSION:append = "-rt"

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-6.1:"

BRANCH = "ti-rt-linux-6.1.y"

SRCREV = "31dc6b6f9309afec8555a584ff1ea5e3f18a30e9"

include ${@ 'recipes-kernel/linux/ti-extras-rt.inc' if d.getVar('TI_EXTRAS') else ''}

PV = "6.1.80+git${SRCPV}"
