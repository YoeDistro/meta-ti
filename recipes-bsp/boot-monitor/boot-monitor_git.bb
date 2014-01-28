DESCRIPTION = "Boot Monitor - TI ARM Boot monitor code"
EXTRA_OEMAKE = "CROSS_COMPILE=${TARGET_PREFIX}"

LICENSE = "BSD"

BOOT_MONITOR_BINARY ?= "skern.bin"
BOOT_MONITOR_IMAGE  ?= "skern-${MACHINE}.bin"
BOOT_MONITOR_MAKE_TARGET  ?= "all"

LIC_FILES_CHKSUM = "file://COPYING;md5=25fe219a6febf6e5bb45beda1b2eb315"

COMPATIBLE_MACHINE = "keystone-evm"

SRC_URI = "git://git.ti.com/keystone-linux/boot-monitor.git;protocol=git;branch=${BRANCH}"

PV = "2.0"
PR = "r0+gitr${SRCPV}"

BRANCH = "master"

S = "${WORKDIR}/git"

#Tag "K2_BM_13.11"
SRCREV = "ba597d183423971e63295cdd59d3c90245e9170a"

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
