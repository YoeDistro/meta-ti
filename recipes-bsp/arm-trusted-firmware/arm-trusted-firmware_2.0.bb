SUMMARY = "ARM Trusted Firmware"
DESCRIPTION = "ARM Trusted Firmware provides a reference implementation of \
Secure World software for ARMv8-A, including Exception Level 3 (EL3) software. \
It provides implementations of various ARM interface standards such as the \
Power State Coordination Interface (PSCI), Trusted Board Boot Requirements \
(TBBR) and Secure monitor code."
HOMEPAGE = "http://infocenter.arm.com/help/topic/com.arm.doc.dui0928e/CJHIDGJF.html"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://license.rst;md5=c709b197e22b81ede21109dbffd5f363"

inherit deploy

DEPENDS += "dtc-native"

PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}/git"

PV_append = "+git${SRCPV}"

BRANCH = "ti-atf"
SRC_URI = "git://git.ti.com/atf/arm-trusted-firmware.git;branch=${BRANCH}"

SRCREV ?= "ee0d515f0577d96ca32278e7cccb6fd768d1b83d"

# Make ATF "aware" of OPTEE, no build dependency
PACKAGECONFIG[optee] = "SPD=opteed"

COMPATIBLE_MACHINE = "k3"
ATFPLATFORM_k3 = "k3"
ATFBOARD_k3 = "generic"

PACKAGECONFIG_k3 = "optee"

CFLAGS[unexport] = "1"
LDFLAGS[unexport] = "1"
AS[unexport] = "1"
LD[unexport] = "1"

do_configure[noexec] = "1"

EXTRA_OEMAKE = 'CROSS_COMPILE="${TARGET_PREFIX}" PLAT="${ATFPLATFORM}" TARGET_BOARD="${ATFBOARD}" ${PACKAGECONFIG_CONFARGS}'

do_compile() {
	oe_runmake all
}

do_compile_append_am65xx-hs-evm() {
	export TI_SECURE_DEV_PKG=${TI_SECURE_DEV_PKG}
	( cd ${S}/build/${ATFPLATFORM}/${ATFBOARD}/release/; \
		mv bl31.bin bl31.bin.unsigned; \
		${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh bl31.bin.unsigned bl31.bin; \
	)
}

do_install() {
	install -d ${D}/boot
	install -m 0644 ${S}/build/${ATFPLATFORM}/${ATFBOARD}/release/bl31.bin ${D}/boot/
	install -m 0644 ${S}/build/${ATFPLATFORM}/${ATFBOARD}/release/bl31/bl31.elf ${D}/boot/
}

do_deploy() {
	install -d ${DEPLOYDIR}
	install -m 0644 ${S}/build/${ATFPLATFORM}/${ATFBOARD}/release/bl31.bin ${DEPLOYDIR}/
	install -m 0644 ${S}/build/${ATFPLATFORM}/${ATFBOARD}/release/bl31/bl31.elf ${DEPLOYDIR}/
}
addtask deploy before do_build after do_compile

FILES_${PN} = "/boot"
SYSROOT_DIRS += "/boot"
