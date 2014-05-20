DESCRIPTION = "TI Inter Process Communication (IPC) Mechanisms (for Uni- and Multi- Processor Configurations)"
HOMEPAGE="http://processors.wiki.ti.com/index.php/Category:IPC"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${S}/ipc-linux.mak;beginline=1;endline=30;md5=f2518e421e230f06fe6d449718d02edc"

DEPENDS += "virtual/kernel"

PR = "r1"

PACKAGE_ARCH = "${MACHINE_ARCH}"

PLATFORM = "UNKNOWN"
PLATFORM_omap5-evm = "OMAP54XX"
PLATFORM_dra7xx-evm = "DRA7XX"
PLATFORM_keystone = "TCI6638"

DAEMON = "UNKNOWN"
DAEMON_omap5-evm = "lad_omap54xx_smp"
DAEMON_dra7xx-evm = "lad_dra7xx"
DAEMON_keystone = "lad_tci6638"

inherit autotools pkgconfig update-rc.d

SRC_URI = "git://git.ti.com/ipc/ipcdev.git;protocol=git \
           file://0002-ipc-Added-installation-prefix-feature-to-products.ma.patch \
           file://tiipclad-daemon.sh \
           "

S = "${WORKDIR}/git"

INITSCRIPT_NAME = "tiipclad-daemon.sh"
INITSCRIPT_PARAMS = "defaults 10"

EXTRA_OEMAKE += "KERNEL_INSTALL_DIR=${STAGING_KERNEL_DIR} TOOLCHAIN_INSTALL_DIR=${TOOLCHAIN_PATH} TOOLCHAIN_LONGNAME=${TOOLCHAIN_SYS} PLATFORM=${PLATFORM} PREFIX=${prefix}"

do_configure() {
    oe_runmake -f ${S}/ipc-linux.mak config
}

do_install_append() {
    install -d ${D}${sysconfdir}/init.d/

    # Modify the tiipclad-daemon.sh script to point to the right
    # lad daemon executable.
    sed -i -e "s/__LAD_DAEMON__/${DAEMON}/" ${WORKDIR}/tiipclad-daemon.sh
    install -c -m 755 ${WORKDIR}/tiipclad-daemon.sh ${D}${sysconfdir}/init.d/${INITSCRIPT_NAME}
}

PACKAGES =+ "${PN}-test"
RDEPENDS_${PN}-test += "${PN}"

FILES_${PN}-test = " \
    ${bindir}/NameServerApp \
    ${bindir}/MessageQApp \
    ${bindir}/MessageQMulti \
    ${bindir}/ping_rpmsg"

