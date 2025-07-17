SECTION = "kernel"
SUMMARY = "Mainline Linux kernel for TI devices"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

inherit ${KERNEL_BASE_CLASS}

require recipes-kernel/linux/ti-kernel.inc

DEPENDS += "gmp-native libmpc-native"

KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT} ${EXTRA_DTC_ARGS}"

S = "${UNPACKDIR}/${BB_GIT_DEFAULT_DESTSUFFIX}"

# 6.12 Mainline version
SRCREV = "adc218676eef25575469234709c2d87185ca223a"
PV = "6.12"

KERNEL_GIT_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/torvalds/linux.git"
BRANCH = "master"

KERNEL_DEFCONFIG = ""

KERNEL_REPRODUCIBILITY_PATCHES = " \
    file://0001-drivers-gpu-drm-msm-registers-improve-reproducibilit.patch \
    file://0001-perf-python-Fix-compile-for-32bit-platforms.patch \
"

DEFCONFIG_NAME = "multi_v7_defconfig"
DEFCONFIG_NAME:omapl138 = "davinci_all_defconfig"
DEFCONFIG_NAME:k3 = "defconfig"
KERNEL_CONFIG_COMMAND = "oe_runmake -C ${S} O=${B} ${DEFCONFIG_NAME}"
