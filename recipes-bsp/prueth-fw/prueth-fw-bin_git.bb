SUMMARY = "PRU Ethernet firmware for AM65xx"

LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://LICENSE.ti;md5=b5aebf0668bdf95621259288c4a46d76"

PV = "1.0.0"

CLEANBROKEN = "1"

COMPATIBLE_MACHINE = "am65xx-evm"

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRCREV = "5d8d6e8b1ec0d95ea29ca5b1c4ca09c14a6b5166"
BRANCH ?= "ti-linux-firmware-4.1.y"

SRC_URI = "git://git.ti.com/processor-firmware/ti-linux-firmware.git;protocol=git;branch=${BRANCH}"

S = "${WORKDIR}/git"

TARGET = "am65x-pru0-prueth-fw.elf am65x-pru1-prueth-fw.elf am65x-rtu0-prueth-fw.elf am65x-rtu1-prueth-fw.elf"

do_install() {
	install -d ${D}${base_libdir}/firmware/ti-pruss
	for f in ${TARGET}; do
		install -m 0644 ${S}/ti-pruss/$f ${D}${base_libdir}/firmware/ti-pruss/$f
	done
}

FILES_${PN} = "${base_libdir}/firmware"

INSANE_SKIP_${PN} = "arch"
