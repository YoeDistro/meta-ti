DESCRIPTION = "TI Inter Process Communication (IPC) Mechanisms (for Uni- and Multi- Processor Configurations)"
HOMEPAGE="http://processors.wiki.ti.com/index.php/Category:IPC"

require ti-ipc.inc

PR = "${INC_PR}.0"

DEPENDS += "virtual/kernel"

SRC_URI += "file://tiipclad-daemon.sh \
            file://0001-Add-kernel-build-dir.patch \
           "

DAEMON = "UNKNOWN"
DAEMON_omap5-evm = "lad_omap54xx_smp"
DAEMON_dra7xx = "lad_dra7xx"
DAEMON_k2hk-evm = "lad_tci6638"
DAEMON_k2l-evm = "lad_tci6630"
DAEMON_k2e-evm = "lad_66ak2e"
DAEMON_k2g-evm = "lad_66ak2g"

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
