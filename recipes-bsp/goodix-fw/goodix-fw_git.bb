DESCRIPTION = "Goodix GT9271 config firmware"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENCE.Spectrum-GT9271;md5=2a6de6be7af1fe46370c684daf27c852"

PV = "1.0.0.0"
PR = "r0"

CLEANBROKEN = "1"

COMPATIBLE_MACHINE = "dra7xx"

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRCREV = "f1a8d1d0131f83bd1a775cde7075eb67c070892c"
BRANCH ?= "ti-linux-firmware-4.1.y"

SRC_URI = "git://git.ti.com/processor-firmware/ti-linux-firmware.git;protocol=git;branch=${BRANCH}"

S = "${WORKDIR}/git"
ORIGIN = "DRA71x-RevA-GT9271_SpecDig_Config.bin"
TARGET = "goodix_9271_cfg.bin"

do_install() {
	install -d ${D}${base_libdir}/firmware
	install -m 0644 ${S}/ti-evm/${ORIGIN} ${D}${base_libdir}/firmware/${TARGET}
}

FILES_${PN} = "${base_libdir}/firmware"
