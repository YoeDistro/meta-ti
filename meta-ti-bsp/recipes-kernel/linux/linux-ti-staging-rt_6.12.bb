require linux-ti-staging_6.12.bb

KERNEL_LOCALVERSION:append = "-rt"

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-6.12:"

BRANCH_ARM32 = "ti-rt-linux-6.12.y-arm32"
SRCREV_ARM32 = "f0e4f5ca0905956c70779b31663f594c08c6a3bc"

BRANCH:ti33x = "${BRANCH_ARM32}"
BRANCH:ti43x = "${BRANCH_ARM32}"
BRANCH:am57xx = "${BRANCH_ARM32}"

SRCREV:ti33x = "${SRCREV_ARM32}"
SRCREV:ti43x = "${SRCREV_ARM32}"
SRCREV:am57xx = "${SRCREV_ARM32}"

include ${@ 'recipes-kernel/linux/ti-extras-rt.inc' if d.getVar('TI_EXTRAS') else ''}
