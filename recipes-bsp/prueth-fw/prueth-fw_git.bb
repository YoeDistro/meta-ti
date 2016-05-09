DESCRIPTION = "PRU Ethernet firmware for AM57xx, AM437x and AM335x"

LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://LICENSE.ti;md5=b5aebf0668bdf95621259288c4a46d76"

PV_am57xx-evm = "1.0.0"
PV_am437x-evm = "2.1.2"
PV_am335x-evm = "2.1.2"

PR = "r2"

CLEANBROKEN = "1"

COMPATIBLE_MACHINE = "am57xx-evm|am437x-evm|am335x-evm"

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRCREV = "4096bf1129c4a707069e685edd44ca64bd51421d"
BRANCH ?= "ti-linux-firmware-4.1.y"

SRC_URI = "git://git.ti.com/processor-firmware/ti-linux-firmware.git;protocol=git;branch=${BRANCH}"

S = "${WORKDIR}/git"

TARGET_am57xx-evm = "am57xx-pru0-prueth-fw.elf am57xx-pru1-prueth-fw.elf"
TARGET_am437x-evm = "am437x-pru0-prueth-fw.elf am437x-pru1-prueth-fw.elf"
TARGET_am335x-evm = "am335x-pru0-prueth-fw.elf am335x-pru1-prueth-fw.elf"

do_install() {
	install -d ${D}${base_libdir}/firmware/ti-pruss
	for f in ${TARGET}; do
		install -m 0644 ${S}/ti-pruss/$f ${D}${base_libdir}/firmware/ti-pruss/$f
	done
}

FILES_${PN} = "${base_libdir}/firmware"

INSANE_SKIP_${PN} = "arch"
