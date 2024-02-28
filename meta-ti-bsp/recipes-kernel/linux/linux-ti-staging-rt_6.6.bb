require linux-ti-staging_6.6.bb

DEFAULT_PREFERENCE = "-1"

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-6.6:"

BRANCH = "ti-rt-linux-6.6.y"

SRCREV = "357d8fe8189aa5c61b4e311ef810f7e0a2c49be9"

include ${@ 'recipes-kernel/linux/ti-extras-rt.inc' if d.getVar('TI_EXTRAS') else ''}

PV = "6.6.14+git"
