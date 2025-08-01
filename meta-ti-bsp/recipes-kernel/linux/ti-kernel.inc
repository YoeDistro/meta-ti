KERNEL_GIT_URI ?= "git://git.ti.com/git/ti-linux-kernel/ti-linux-kernel.git"
KERNEL_GIT_PROTOCOL ?= "https"
KERNEL_GIT_BRANCH ?= "branch=${BRANCH}"

KERNEL_DEFCONFIG ?= "file://defconfig"

KERNEL_BASE_CLASS ?= "kernel"

KERNEL_REPRODUCIBILITY_PATCHES ?= ""

SRC_URI = " \
    ${KERNEL_GIT_URI};protocol=${KERNEL_GIT_PROTOCOL};${KERNEL_GIT_BRANCH} \
    ${KERNEL_DEFCONFIG} \
    ${KERNEL_REPRODUCIBILITY_PATCHES} \
"
