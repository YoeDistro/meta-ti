DESCRIPTION = "TI Inter Process Communication (IPC) Mechanisms (for Uni- and Multi- Processor Configurations)"
HOMEPAGE="http://processors.wiki.ti.com/index.php/Category:IPC"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${S}/ipc-linux.mak;beginline=1;endline=30;md5=abd112f156e5eb9b0f3e202e48747f9a"

DEPENDS += "virtual/kernel"

PV = "3.30.01.12"

BRANCH = "master"
SRC_URI = "git://git.ti.com/ipc/ipcdev.git;protocol=git;branch=${BRANCH} \
           file://tiipclad-daemon.sh \
           "
SRCREV = "8deec9b295a5700b26449986927c77d60c5c0e5f"

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"

PLATFORM = "UNKNOWN"
PLATFORM_omap5-evm = "OMAP54XX"
PLATFORM_dra7xx = "DRA7XX"
PLATFORM_keystone = "TCI6638"

DAEMON = "UNKNOWN"
DAEMON_omap5-evm = "lad_omap54xx_smp"
DAEMON_dra7xx = "lad_dra7xx"
DAEMON_keystone = "lad_tci6638"

inherit autotools pkgconfig update-rc.d

INITSCRIPT_NAME = "tiipclad-daemon.sh"
INITSCRIPT_PARAMS = "defaults 10"

EXTRA_OECONF += "PLATFORM=${PLATFORM} KERNEL_INSTALL_DIR=${STAGING_KERNEL_DIR}"

do_configure() {
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

