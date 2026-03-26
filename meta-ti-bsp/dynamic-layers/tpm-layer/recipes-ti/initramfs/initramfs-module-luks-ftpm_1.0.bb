SUMMARY = "initramfs support for LUKS encryption with fTPM"
DESCRIPTION = "Provides LUKS2 full disk encryption using firmware TPM (fTPM) for key management on TI K3 platforms"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

# Only build for platforms with optee-ftpm support
COMPATIBLE_MACHINE = "null"
COMPATIBLE_MACHINE:k3 = "${@bb.utils.contains('MACHINE_FEATURES', 'optee-ftpm', '.*', 'null', d)}"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI = "file://luksftpm"

S = "${UNPACKDIR}"

do_install() {
    install -d ${D}/init.d
    # Install as 85-luksftpm (runs after udev at 01, before rootfs at 90)
    install -m 0755 ${UNPACKDIR}/luksftpm ${D}/init.d/85-luksftpm
}

FILES:${PN} = "/init.d/85-luksftpm"

# Runtime dependencies
RDEPENDS:${PN} = "\
    initramfs-framework-base \
    busybox \
    kmod \
    cryptsetup \
    tpm2-tools \
    tpm2-tss \
    libtss2-tcti-device \
    optee-client \
    optee-ftpm \
    e2fsprogs-e2fsck \
    e2fsprogs-resize2fs \
    e2fsprogs-tune2fs \
    util-linux-blkid \
    kernel-module-tpm-ftpm-tee \
"

PACKAGE_ARCH = "${MACHINE_ARCH}"
