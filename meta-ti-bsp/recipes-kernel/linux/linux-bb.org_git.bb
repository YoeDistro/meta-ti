SECTION = "kernel"
SUMMARY = "BeagleBoard.org Linux kernel"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

COMPATIBLE_MACHINE = "beagle.*"

inherit kernel

require recipes-kernel/linux/ti-kernel.inc

DEPENDS += "gmp-native libmpc-native"

KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT} ${EXTRA_DTC_ARGS}"

S = "${WORKDIR}/git"

# 5.10.145 version
SRCREV = "9b11aaf2cdb1861ca74dc69d032a0f94cdd32bd6"
PV = "5.10.145+git${SRCPV}"
BRANCH = "5.10"

# 5.10.153 version
SRCREV:k3 = "11ebcc09f32669fac8254dff56d500f86c4c2caf"
PV:k3 = "5.10.153+git${SRCPV}"
BRANCH:k3 = "5.10-arm64"

SRC_URI = "git://github.com/beagleboard/linux.git;protocol=https;branch=${BRANCH} \
           file://init_disassemble_info-signature-changes-causes-compile-failures.patch"

DEFCONFIG_NAME = "bb.org_defconfig"
KERNEL_CONFIG_COMMAND = "oe_runmake -C ${S} O=${B} ${DEFCONFIG_NAME}"

kernel_do_compile:append() {
	oe_runmake dtbs CC="${KERNEL_CC} $cc_extra " LD="${KERNEL_LD}" ${KERNEL_EXTRA_ARGS}
}
