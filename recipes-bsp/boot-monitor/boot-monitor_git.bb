DESCRIPTION = "Boot Monitor - TI ARM Boot monitor code"
EXTRA_OEMAKE = "CROSS_COMPILE=${TARGET_PREFIX}"

LICENSE = "BSD"

BOOT_MONITOR_BINARY ?= "skern.bin"
BOOT_MONITOR_IMAGE  ?= "skern-${MACHINE}.bin"

LIC_FILES_CHKSUM = "file://COPYING;md5=25fe219a6febf6e5bb45beda1b2eb315"

COMPATIBLE_MACHINE = "keystone"

SRC_URI = "git://git.ti.com/keystone-linux/boot-monitor.git;protocol=git;branch=${BRANCH}"

PV = "2.0"
PR = "r1+gitr${SRCPV}"

BRANCH = "master"

S = "${WORKDIR}/git"

#Tag "K2_BM_14.05"
SRCREV = "0e3ffe1ea4a0cee38ae2406901b7cf4d5324b5e9"

do_compile () {
	unset LDFLAGS
	unset CFLAGS
	unset CPPFLAGS
	oe_runmake ${BOOT_MONITOR_MAKE_TARGET}
}

inherit deploy

addtask deploy before do_build after do_compile

do_deploy () {
	install -d ${DEPLOYDIR}
	install ${S}/${BOOT_MONITOR_BINARY} ${DEPLOYDIR}/${BOOT_MONITOR_IMAGE}
}
