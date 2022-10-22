SECTION = "kernel"
SUMMARY = "Linux-next kernel for TI devices"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

inherit kernel

require recipes-kernel/linux/ti-kernel.inc

DEPENDS += "gmp-native libmpc-native"

KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT} ${EXTRA_DTC_ARGS}"

S = "${WORKDIR}/git"

# 6.1.0-rc1+ version
SRCREV = "4d48f589d294ddc5e01d3b0dc7cecc55324c05ca"
PV = "6.1.0-rc1+git${SRCPV}"

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/next/linux-next.git;protocol=https;branch=master"

def list_dtbs(dts_dir, dts_subdir):
    import os
    import fnmatch
    matches = []

    for root,dirnames,filenames in os.walk(os.path.join(dts_dir, dts_subdir)):
        for filename in fnmatch.filter(filenames, '*.dtb'):
            matches.append(os.path.join(dts_subdir, filename))
        for filename in fnmatch.filter(filenames, '*.dtbo'):
            matches.append(os.path.join(dts_subdir, filename))
    return ' '.join(matches)

DTS_SUBDIR = ""
DTS_SUBDIR:k3 = "ti"
KERNEL_DEVICETREE = "${@list_dtbs('${B}/arch/${ARCH}/boot/dts', '${DTS_SUBDIR}')}"
DEFCONFIG_NAME = "multi_v7_defconfig"
DEFCONFIG_NAME:k3 = "defconfig"
KERNEL_CONFIG_COMMAND = "oe_runmake -C ${S} O=${B} ${DEFCONFIG_NAME}"

kernel_do_compile:append() {
	oe_runmake dtbs CC="${KERNEL_CC} $cc_extra " LD="${KERNEL_LD}" ${KERNEL_EXTRA_ARGS}
}
