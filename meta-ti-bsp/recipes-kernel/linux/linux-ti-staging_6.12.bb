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
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-6.12:"

KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT} \
		      ${EXTRA_DTC_ARGS}"

S = "${UNPACKDIR}/${BB_GIT_DEFAULT_DESTSUFFIX}"

BRANCH ?= "ti-linux-6.12.y"

SRCREV ?= "2edd47b56fbddaa47a5b85fe3440bb3516bca8ea"
PV = "6.12.49+git"

KERNEL_REPRODUCIBILITY_PATCHES = " \
    file://0001-drivers-gpu-drm-msm-registers-improve-reproducibilit.patch \
"

# Special configuration for remoteproc/rpmsg IPC modules
module_conf_rpmsg_client_sample = "blacklist rpmsg_client_sample"
module_conf_ti_k3_r5_remoteproc = "softdep ti_k3_r5_remoteproc pre: virtio_rpmsg_bus"
module_conf_ti_k3_dsp_remoteproc = "softdep ti_k3_dsp_remoteproc pre: virtio_rpmsg_bus"
KERNEL_MODULE_PROBECONF += "rpmsg_client_sample ti_k3_r5_remoteproc ti_k3_dsp_remoteproc"
