SECTION = "kernel"
SUMMARY = "Linux kernel for TI devices"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

require recipes-kernel/linux/ti-kernel.inc

inherit ${KERNEL_BASE_CLASS}

require recipes-kernel/linux/setup-defconfig.inc
include ${@ 'recipes-kernel/linux/ti-extras.inc' if d.getVar('TI_EXTRAS') else ''}

DEPENDS += "gmp-native libmpc-native"

# Look in the generic major.minor directory for files
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-6.18:"

KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT} \
		      ${EXTRA_DTC_ARGS}"

S = "${UNPACKDIR}/${BB_GIT_DEFAULT_DESTSUFFIX}"

BRANCH ?= "ti-linux-6.18.y"

SRCREV ?= "7e2c5ee21e318f67edb760f67fe16d41b1dc5a1e"
PV = "6.18.12+git"

KERNEL_REPRODUCIBILITY_PATCHES = " \
    file://0001-perf-python-Fix-compile-for-32bit-platforms.patch \
    file://0001-arm-Remove-build-path-from-generated-mach-types.h.patch \
"

# Special configuration for remoteproc/rpmsg IPC modules
module_conf_rpmsg_client_sample = "blacklist rpmsg_client_sample"
module_conf_ti_k3_r5_remoteproc = "softdep ti_k3_r5_remoteproc pre: virtio_rpmsg_bus"
module_conf_ti_k3_dsp_remoteproc = "softdep ti_k3_dsp_remoteproc pre: virtio_rpmsg_bus"
KERNEL_MODULE_PROBECONF += "rpmsg_client_sample ti_k3_r5_remoteproc ti_k3_dsp_remoteproc"

DEFAULT_PREFERENCE = "-1"
