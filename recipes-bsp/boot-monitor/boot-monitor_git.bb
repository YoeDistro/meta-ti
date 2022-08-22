DESCRIPTION = "Boot Monitor - TI ARM Boot monitor code"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=25fe219a6febf6e5bb45beda1b2eb315"

COMPATIBLE_MACHINE = "keystone"
PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS = "u-boot-mkimage-native"

SRC_URI = "git://git.ti.com/git/processor-firmware/ks2-boot-monitor.git;protocol=https;branch=${BRANCH}"

PV = "4.0+git${SRCPV}"

BRANCH = "master"

S = "${WORKDIR}/git"

SRCREV = "d57d5c1d8f0b0b1487484d9ceb1595bd4507231a"

BOOT_MONITOR_IMAGE  ?= "skern-${BOOT_MONITOR_MAKE_TARGET}.bin"

FLOATABI = "${@bb.utils.contains("TUNE_FEATURES", "vfp", bb.utils.contains("TUNE_FEATURES", "callconvention-hard", " -mfloat-abi=hard", " -mfloat-abi=softfp", d), "" ,d)}"

EXTRA_OEMAKE = "CROSS_COMPILE=${TARGET_PREFIX} CC="${TARGET_PREFIX}gcc ${TOOLCHAIN_OPTIONS} ${FLOATABI}" LD="${TARGET_PREFIX}gcc ${TOOLCHAIN_OPTIONS} ${FLOATABI}""

FILES_${PN} = "/boot"

inherit deploy

do_compile () {
	unset LDFLAGS
	unset CFLAGS
	unset CPPFLAGS
	oe_runmake image_${BOOT_MONITOR_MAKE_TARGET}
}

do_install () {
	install -d ${D}/boot
	install -m 0644 ${S}/${BOOT_MONITOR_IMAGE} ${D}/boot/
}

do_deploy () {
	install -d ${DEPLOYDIR}
	install -m 0644 ${S}/${BOOT_MONITOR_IMAGE} ${DEPLOYDIR}/
}

addtask deploy before do_build after do_compile
