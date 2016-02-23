DESCRIPTION = "PRU Ethernet firmware for AM57xx"

LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://LICENSE.ti;md5=b5aebf0668bdf95621259288c4a46d76"

PV = "1.0.0"
PR = "r0"

CLEANBROKEN = "1"

COMPATIBLE_MACHINE = "am57xx-evm"

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRCREV = "4ead3b8c189f217ee2c01ea3c56ef08d6a517f28"
BRANCH ?= "ti-linux-firmware-4.1.y"

SRC_URI = "git://git.ti.com/processor-firmware/ti-linux-firmware.git;protocol=git;branch=${BRANCH}"

S = "${WORKDIR}/git"

TARGET = "am57xx-pru0-prueth-fw.elf am57xx-pru1-prueth-fw.elf"

do_install() {
	install -d ${D}${base_libdir}/firmware/ti-pruss
	for f in ${TARGET}; do
		install -m 0644 ${S}/ti-pruss/$f ${D}${base_libdir}/firmware/ti-pruss/$f
	done
}

FILES_${PN} = "${base_libdir}/firmware"

INSANE_SKIP_${PN} = "arch"
