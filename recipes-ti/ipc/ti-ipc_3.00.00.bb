DESCRIPTION="This support the communication between processors \
in a multi-processor environment and communication to peripherals. \
This communication includes message passing, streams, and linked lists. \
These modules work transparently in both uni-processor and multi-processor \
configurations."

HOMEPAGE="http://processors.wiki.ti.com/index.php/Category:IPC"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://products.mak;beginline=2;endline=30;md5=195feadf798bb4165bcb1a23ffd50dbb"
SECTION = "console"
COMPATIBLE_MACHINE = "keystone"
TARGET_PLATFORM = "tci6638"

PR = "r0"

BRANCH ?= "master"
# The following commit corresponds to 3.00.04.29
SRCREV = "12794ea33870b782bffe1fe4398e86e93fb64396"

SRC_URI = " \
    git://git.ti.com/ipc/ipcdev.git;protocol=git;branch=${BRANCH} \
    file://tiipclad-daemon.sh"

S = "${WORKDIR}/git"

export PLATFORM = "${TARGET_PLATFORM}"

export PARALLEL_MAKE = ""

PACKAGES =+ "${PN}-test"

FILES_${PN}-test = " \
    ${bindir}/NameServerApp \
    ${bindir}/MessageQApp \
    ${bindir}/MessageQMulti \
    ${bindir}/ping_rpmsg"

DEPENDS += "virtual/kernel"

EXTRA_OECONF += "KERNEL_INSTALL_DIR=${STAGING_KERNEL_DIR}"
inherit autotools

INITSCRIPT_NAME = "tiipclad-daemon.sh"
INITSCRIPT_PARAMS = "defaults 10"

inherit update-rc.d

do_install_append() {
	install -d ${D}${sysconfdir}/init.d/
	install -c -m 755 ${WORKDIR}/tiipclad-daemon.sh ${D}${sysconfdir}/init.d/${INITSCRIPT_NAME}
}
