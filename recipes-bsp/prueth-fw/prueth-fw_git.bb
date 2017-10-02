DESCRIPTION = "PRU Ethernet firmware for AM57xx, AM437x, AM335x and K2G"

LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://LICENSE.ti;md5=b5aebf0668bdf95621259288c4a46d76"

PV = "5.1.4"
PR = "r0"

CLEANBROKEN = "1"

COMPATIBLE_MACHINE = "am57xx-evm|am437x-evm|am335x-evm|k2g"

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRCREV = "b9d9e4bef2f32d4b55a63ecfec2e21f7b1eaaa34"
BRANCH ?= "ti-linux-firmware-4.1.y"

SRC_URI = "git://git.ti.com/processor-firmware/ti-linux-firmware.git;protocol=git;branch=${BRANCH}"

S = "${WORKDIR}/git"

TARGET_am57xx-evm = "am57xx-pru0-prueth-fw.elf am57xx-pru1-prueth-fw.elf"
TARGET_am437x-evm = "am437x-pru0-prueth-fw.elf am437x-pru1-prueth-fw.elf"
TARGET_am335x-evm = "am335x-pru0-prueth-fw.elf am335x-pru1-prueth-fw.elf"
TARGET_k2g = "k2g-pru0-prueth-fw.elf k2g-pru1-prueth-fw.elf"

do_install() {
	install -d ${D}${base_libdir}/firmware/ti-pruss
	for f in ${TARGET}; do
		install -m 0644 ${S}/ti-pruss/$f ${D}${base_libdir}/firmware/ti-pruss/$f
	done
}

FILES_${PN} = "${base_libdir}/firmware"

INSANE_SKIP_${PN} = "arch"
