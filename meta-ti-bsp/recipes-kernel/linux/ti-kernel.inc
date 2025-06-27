# Tell the kernel class to install the DTBs in the same directory structure as
# the kernel.
KERNEL_DTBDEST = "${KERNEL_IMAGEDEST}/dtb"
KERNEL_DTBVENDORED = "1"

KERNEL_GIT_URI ?= "git://git.ti.com/git/ti-linux-kernel/ti-linux-kernel.git"
KERNEL_GIT_PROTOCOL ?= "https"
KERNEL_GIT_BRANCH ?= "branch=${BRANCH}"

KERNEL_DEFCONFIG ?= "file://defconfig"

KERNEL_REPRODUCIBILITY_PATCHES ?= ""

SRC_URI = " \
    ${KERNEL_GIT_URI};protocol=${KERNEL_GIT_PROTOCOL};${KERNEL_GIT_BRANCH} \
    ${KERNEL_DEFCONFIG} \
    ${KERNEL_REPRODUCIBILITY_PATCHES} \
"
