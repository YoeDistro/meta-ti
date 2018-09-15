SUMMARY = "ARM Trusted Firmware"
DESCRIPTION = "ARM Trusted Firmware provides a reference implementation of \
Secure World software for ARMv8-A, including Exception Level 3 (EL3) software. \
It provides implementations of various ARM interface standards such as the \
Power State Coordination Interface (PSCI), Trusted Board Boot Requirements \
(TBBR) and Secure monitor code."
HOMEPAGE = "http://infocenter.arm.com/help/topic/com.arm.doc.dui0928e/CJHIDGJF.html"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://license.rst;md5=e927e02bca647e14efd87e9e914b2443"

inherit deploy

DEPENDS += "dtc-native"

PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}/git"

PV_append = "+git${SRCPV}"

BRANCH = "ti-atf"
SRC_URI = "git://git.ti.com/atf/arm-trusted-firmware.git;branch=${BRANCH}"

SRCREV ?= "d7b4c6e1fdb4553fc23a02776bca111bdcdf6b9f"

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
