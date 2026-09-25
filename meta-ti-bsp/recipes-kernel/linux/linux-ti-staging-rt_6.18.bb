require linux-ti-staging_6.18.bb

KERNEL_LOCALVERSION:append = "-rt"

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-6.18:"

BRANCH_ARM64 = "ti-linux-6.18.y"
BRANCH = "${BRANCH_ARM64}"

BRANCH_ARM32 = "ti-rt-linux-6.18.y-arm32"
SRCREV_ARM32 = "d1be33790a0c6d9241b08a8bcf0b702729ac2b79"
PV_ARM32 = "6.18.51+git"

BRANCH:ti33x = "${BRANCH_ARM32}"
BRANCH:ti43x = "${BRANCH_ARM32}"
BRANCH:am57xx = "${BRANCH_ARM32}"

SRCREV:ti33x = "${SRCREV_ARM32}"
SRCREV:ti43x = "${SRCREV_ARM32}"
SRCREV:am57xx = "${SRCREV_ARM32}"

PV:ti33x = "${PV_ARM32}"
PV:ti43x = "${PV_ARM32}"
PV:am57xx = "${PV_ARM32}"

include ${@ 'recipes-kernel/linux/ti-extras-rt.inc' if d.getVar('TI_EXTRAS') else ''}
