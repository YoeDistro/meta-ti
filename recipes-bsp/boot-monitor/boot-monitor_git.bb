DESCRIPTION = "Boot Monitor - TI ARM Boot monitor code"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=25fe219a6febf6e5bb45beda1b2eb315"

COMPATIBLE_MACHINE = "keystone"
PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = "git://git.ti.com/keystone-linux/boot-monitor.git;protocol=git;branch=${BRANCH}"

PV = "2.0+git${SRCPV}"
PR = "r4"

BRANCH = "master"

S = "${WORKDIR}/git"

#Tag "K2_BM_15.07"
SRCREV = "62d198687a34a9df375c1686d75ad4f85515dcf9"

BOOT_MONITOR_BINARY ?= "skern.bin"
BOOT_MONITOR_IMAGE  ?= "skern-${MACHINE}.bin"

FLOATABI = "${@base_contains("TUNE_FEATURES", "vfp", base_contains("TUNE_FEATURES", "callconvention-hard", " -mfloat-abi=hard", " -mfloat-abi=softfp", d), "" ,d)}"

EXTRA_OEMAKE = "CROSS_COMPILE=${TARGET_PREFIX} CC="${TARGET_PREFIX}gcc ${TOOLCHAIN_OPTIONS} ${FLOATABI}" LD="${TARGET_PREFIX}gcc ${TOOLCHAIN_OPTIONS} ${FLOATABI}""

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
