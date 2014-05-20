DESCRIPTION = "TI Inter Process Communication (IPC) Mechanisms (for Uni- and Multi- Processor Configurations)"
HOMEPAGE = "https://git.ti.com/ipc/pages/Home"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${S}/ipc-linux.mak;beginline=1;endline=30;md5=f2518e421e230f06fe6d449718d02edc"

DEPENDS += "virtual/kernel"

PR = "r1"

PACKAGE_ARCH = "${MACHINE_ARCH}"

PLATFORM_omap5-evm = "OMAP54XX"
PLATFORM_dra7xx-evm = "DRA7XX"

inherit autotools pkgconfig

SRC_URI = "git://git.ti.com/ipc/ipcdev.git;protocol=git \
           file://0002-ipc-Added-installation-prefix-feature-to-products.ma.patch \
           "

SRCREV = "c18fece0c9f0dc76361dc1ee11b7ee2417479847"

S = "${WORKDIR}/git"

FILESEXTRAPATHS_prepend := "${THISDIR}:"

EXTRA_OEMAKE += "KERNEL_INSTALL_DIR=${STAGING_KERNEL_DIR} TOOLCHAIN_INSTALL_DIR=${TOOLCHAIN_PATH} TOOLCHAIN_LONGNAME=${TOOLCHAIN_SYS} PLATFORM=${PLATFORM} PREFIX=${prefix}"

do_configure() {
	oe_runmake -f ${S}/ipc-linux.mak config
}
