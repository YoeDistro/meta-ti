SECTION = "kernel"
SUMMARY = "Linux kernel for TI devices"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

inherit kernel

DEFCONFIG_BUILDER = "${S}/ti_config_fragments/defconfig_builder.sh"
require recipes-kernel/linux/setup-defconfig.inc
require recipes-kernel/linux/cmem.inc
require recipes-kernel/linux/ti-uio.inc
require recipes-kernel/linux/bundle-devicetree.inc
require recipes-kernel/linux/kernel-rdepends.inc
require recipes-kernel/linux/ti-kernel.inc

# Look in the generic major.minor directory for files
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-5.10:"

KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT} \
		      ${EXTRA_DTC_ARGS}"

S = "${WORKDIR}/git"

BRANCH = "ti-linux-5.10.y"

SRCREV = "dcc6bedb2c2bdb509709e4ae08303206e95ce6c2"
PV = "5.10.65+git${SRCPV}"

# Append to the MACHINE_KERNEL_PR so that a new SRCREV will cause a rebuild
MACHINE_KERNEL_PR:append = "b"
PR = "${MACHINE_KERNEL_PR}"

KERNEL_GIT_URI = "git://git.ti.com/ti-linux-kernel/ti-linux-kernel.git"
KERNEL_GIT_PROTOCOL = "git"
SRC_URI += "${KERNEL_GIT_URI};protocol=${KERNEL_GIT_PROTOCOL};branch=${BRANCH} \
            file://defconfig"

FILES:${KERNEL_PACKAGE_NAME}-devicetree += "/${KERNEL_IMAGEDEST}/*.itb"

# Special configuration for remoteproc/rpmsg IPC modules
module_conf_rpmsg_client_sample = "blacklist rpmsg_client_sample"
module_conf_ti_k3_r5_remoteproc = "softdep ti_k3_r5_remoteproc pre: virtio_rpmsg_bus"
module_conf_ti_k3_dsp_remoteproc = "softdep ti_k3_dsp_remoteproc pre: virtio_rpmsg_bus"
KERNEL_MODULE_PROBECONF += "rpmsg_client_sample ti_k3_r5_remoteproc ti_k3_dsp_remoteproc"
KERNEL_MODULE_AUTOLOAD:append:j7 = " rpmsg_kdrv_switch"

# Disable SA2UL for AM64x HS and J7200 HS
module_conf_sa2ul:am64xx-hs-evm = "blacklist sa2ul"
KERNEL_MODULE_PROBECONF:append:am64xx-hs-evm = " sa2ul"
module_conf_sa2ul:j7200-hs-evm = "blacklist sa2ul"
KERNEL_MODULE_PROBECONF:append:j7200-hs-evm = " sa2ul"
