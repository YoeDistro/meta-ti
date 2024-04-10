require linux-ti-staging_6.6.bb

DEFAULT_PREFERENCE = "-1"

KERNEL_LOCALVERSION:append = "-rt"

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-6.6:"

BRANCH = "ti-rt-linux-6.6.y"

SRCREV = "82fd2ecb48b6bdb66081cb2f24b9bdfce218dea6"

include ${@ 'recipes-kernel/linux/ti-extras-rt.inc' if d.getVar('TI_EXTRAS') else ''}

PV = "6.6.23+git${SRCPV}"
