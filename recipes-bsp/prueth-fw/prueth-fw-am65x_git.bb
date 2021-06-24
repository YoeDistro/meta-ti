SUMMARY = "PRU Ethernet firmware for AM65x SR1.0"

LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://LICENSE.ti;md5=b5aebf0668bdf95621259288c4a46d76"

PV = "08.00.00.20"

CLEANBROKEN = "1"

COMPATIBLE_MACHINE = "am65xx"

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRCREV = "517b0cd8d8d80e91f1f7d80f035e6af041c39726"
BRANCH ?= "ti-linux-firmware"

SRC_URI = "git://git.ti.com/processor-firmware/ti-linux-firmware.git;protocol=git;branch=${BRANCH}"

S = "${WORKDIR}/git"

TARGET = " \
    am65x-pru0-prueth-fw.elf \
    am65x-pru1-prueth-fw.elf \
    am65x-rtu0-prueth-fw.elf \
    am65x-rtu1-prueth-fw.elf \
"

do_install() {
	install -d ${D}${base_libdir}/firmware/ti-pruss
	for f in ${TARGET}; do
		install -m 0644 ${S}/ti-pruss/$f ${D}${base_libdir}/firmware/ti-pruss/$f
	done
}

FILES_${PN} = "${base_libdir}/firmware"

INSANE_SKIP_${PN} = "arch"
