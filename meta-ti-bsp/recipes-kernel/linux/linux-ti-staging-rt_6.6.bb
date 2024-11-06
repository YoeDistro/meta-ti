require linux-ti-staging_6.6.bb

KERNEL_LOCALVERSION:append = "-rt"

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-6.6:"

BRANCH = "ti-rt-linux-6.6.y"

SRCREV = "4143633c95dc50cb5e2c695629d8b6302ef8de81"

include ${@ 'recipes-kernel/linux/ti-extras-rt.inc' if d.getVar('TI_EXTRAS') else ''}

PV = "6.6.44+git"
