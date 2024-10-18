require linux-ti-staging_6.1.bb

KERNEL_LOCALVERSION:append = "-rt"

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-6.1:"

BRANCH = "ti-rt-linux-6.1.y"

SRCREV = "451df8c0a2a3bcf0656b2f6fdc49d6fb4d05f186"

include ${@ 'recipes-kernel/linux/ti-extras-rt.inc' if d.getVar('TI_EXTRAS') else ''}

PV = "6.1.105+git"
