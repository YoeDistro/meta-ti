DESCRIPTION = "Cortex-M3 binary blob for suspend-resume"

LICENSE = "TI-TSPA"
LIC_FILES_CHKSUM = "file://License.txt;md5=7bdc54a749ab7a7dea999d25d99a41b8"

PV = "1.9.2"
PR = "r0"

SRCREV = "fb484c5e54f2e31cf0a338d2927a06a2870bcc2c"
BRANCH ?= "ti-v4.1.y"

SRC_URI = "git://git.ti.com/git/processor-firmware/ti-amx3-cm3-pm-firmware.git;protocol=https;branch=${BRANCH}"

S = "${WORKDIR}/git"

do_compile() {
	make CROSS_COMPILE="${TARGET_PREFIX}" CC="${TARGET_PREFIX}gcc ${TOOLCHAIN_OPTIONS} ${SECURITY_NOPIE_CFLAGS} ${DEBUG_PREFIX_MAP}"
}

do_install() {
	install -d ${D}${nonarch_base_libdir}/firmware
	install -m 0644 bin/am335x-pm-firmware.elf ${D}${nonarch_base_libdir}/firmware/
	install -m 0644 bin/*-scale-data.bin ${D}${nonarch_base_libdir}/firmware/
}

FILES:${PN} += "${nonarch_base_libdir}/firmware"

COMPATIBLE_MACHINE = "(ti-soc)"
TOOLCHAIN = "gcc"
