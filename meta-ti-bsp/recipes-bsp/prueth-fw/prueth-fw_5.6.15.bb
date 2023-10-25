SUMMARY = "PRU Ethernet firmware for AM57xx, AM437x and AM335x"

require recipes-bsp/ti-linux-fw/ti-linux-fw.inc

PR = "${INC_PR}.0"

COMPATIBLE_MACHINE = "ti33x|ti43x|am57xx"

PACKAGE_ARCH = "${MACHINE_ARCH}"

TARGET = ""
TARGET:ti33x = "am335x-pru0-prueth-fw.elf am335x-pru1-prueth-fw.elf"
TARGET:ti43x = "am437x-pru0-prueth-fw.elf am437x-pru1-prueth-fw.elf"
TARGET:am57xx = "am57xx-pru0-prueth-fw.elf am57xx-pru1-prueth-fw.elf"

do_install() {
	install -d ${D}${nonarch_base_libdir}/firmware/ti-pruss
	for f in ${TARGET}; do
		install -m 0644 ${S}/ti-pruss/$f ${D}${nonarch_base_libdir}/firmware/ti-pruss/$f
	done
}
