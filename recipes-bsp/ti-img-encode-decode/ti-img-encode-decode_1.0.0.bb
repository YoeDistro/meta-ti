SUMMARY = "Kernel drivers for the IMG VXE384 MP2 and D5520 encoder/decoder found in TI SoCs"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://driver/common/dq.c;beginline=1;endline=16;md5=e015e28ff5e88576dab9fdf641e3dbfe"

inherit module

MACHINE_KERNEL_PR_append = "g"
PR = "${MACHINE_KERNEL_PR}"

COMPATIBLE_MACHINE = "j7-evm"

SRCREV = "0eba3678300f649e49f03d92d1b624cf0e008008"

EXTRA_OEMAKE = "KPATH=${STAGING_KERNEL_DIR} KCONF=${STAGING_KERNEL_BUILDDIR}"
TARGET_CC_ARCH += "${LDFLAGS}"

BRANCH = "master"
SRC_URI = "git://git.ti.com/jacinto7_multimedia/ti-img-encode-decode.git;protocol=git;branch=${BRANCH}"

S = "${WORKDIR}/git"

do_compile() {
	oe_runmake all -C ${S}/linux/decoder
	oe_runmake all -C ${S}/linux/encoder
}

do_install() {
	install -d ${D}/${base_libdir}/modules/${KERNEL_VERSION}/extra
	install -m 644 ${S}/linux/decoder/vxd-dec.ko ${D}/${base_libdir}/modules/${KERNEL_VERSION}/extra
	install -m 644 ${S}/linux/encoder/vxe-enc.ko ${D}/${base_libdir}/modules/${KERNEL_VERSION}/extra
}
