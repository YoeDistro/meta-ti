require linux-ti-mainline_git.bb

SUMMARY = "Linux-next kernel for TI devices"

# 6.6.0-rc3+ version
SRCREV = "6465e260f48790807eef06b583b38ca9789b6072"
PV = "6.6.0-rc3+git"

KERNEL_GIT_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/next/linux-next.git"

KERNEL_REPRODUCIBILITY_PATCHES = ""
