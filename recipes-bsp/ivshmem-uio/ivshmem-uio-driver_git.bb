SUMMARY =  "Kernel driver for IVSHMEM based UIO driver"
DESCRIPTION = "Kernel module which registers a UIO (userspace io) device for inter VM shared memory"
HOMEPAGE = "https://github.com/henning-schild-work/ivshmem-guest-code"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=0546a27aad86c83b75ad4ee6133e9d5e"

inherit module

RDEPENDS_${PN} = "jailhouse"

PROTOCOL = "git"
BRANCH = "jailhouse"
SRCREV = "f3ad79881bebb6c6068966ee3d265d8034c20492"
SRC_URI = "git://github.com/henning-schild-work/ivshmem-guest-code.git;protocol=${PROTOCOL};branch=${BRANCH}"

S = "${WORKDIR}/git"

EXTRA_OEMAKE += 'KDIR="${STAGING_KERNEL_DIR}"'

COMPATIBLE_MACHINE = "(ti-soc)"

do_compile_prepend() {
    cd ${S}/kernel_module/uio
}

do_install() {
    install -d ${D}/${base_libdir}/modules/${KERNEL_VERSION}/extra
    install -m 644 ${S}/kernel_module/uio/uio_ivshmem.ko ${D}/${base_libdir}/modules/${KERNEL_VERSION}/extra
}
