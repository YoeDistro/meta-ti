DESCRIPTION = "TI Inter Process Communication (IPC) Mechanisms (for Uni- and Multi- Processor Configurations)"
HOMEPAGE="http://processors.wiki.ti.com/index.php/Category:IPC"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${S}/ipc-linux.mak;beginline=1;endline=30;md5=7b327f9b710fd7c95e545b91cec79255"

DEPENDS += "virtual/kernel"

PV = "3.36.01.11"

BRANCH = "3.36"
SRC_URI = "git://git.ti.com/ipc/ipcdev.git;protocol=git;branch=${BRANCH} \
           file://tiipclad-daemon.sh \
           file://0001-Add-kernel-build-dir.patch \
           "
# Commit corresponds to 3.36.01.11
SRCREV = "2a4256289d462d93a7e00a6ffb10bd46081a9500"

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"

PLATFORM = "UNKNOWN"
PLATFORM_omap5-evm = "OMAP54XX"
PLATFORM_dra7xx = "DRA7XX"
PLATFORM_k2hk-evm = "TCI6638"
PLATFORM_k2l-evm = "TCI6630"
PLATFORM_k2e-evm = "66AK2E"

DAEMON = "UNKNOWN"
DAEMON_omap5-evm = "lad_omap54xx_smp"
DAEMON_dra7xx = "lad_dra7xx"
DAEMON_k2hk-evm = "lad_tci6638"
DAEMON_k2l-evm = "lad_tci6630"
DAEMON_k2e-evm = "lad_66ak2e"

inherit autotools-brokensep pkgconfig update-rc.d

INITSCRIPT_NAME = "tiipclad-daemon.sh"
INITSCRIPT_PARAMS = "defaults 10"

EXTRA_OECONF += "PLATFORM=${PLATFORM} KERNEL_INSTALL_DIR=${STAGING_KERNEL_DIR} KERNEL_BUILD_DIR=${STAGING_KERNEL_BUILDDIR}"

do_configure() {
    ( cd ${S}; autoreconf -f -i -s )
    oe_runconf
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
