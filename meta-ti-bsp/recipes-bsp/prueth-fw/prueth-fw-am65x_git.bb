SUMMARY = "PRU Ethernet firmware for AM65x SR1.0"

require recipes-bsp/ti-linux-fw/ti-linux-fw.inc

PV = "${PRUETH_FW_AM65X_VERSION}"
PR = "${INC_PR}.0"

COMPATIBLE_MACHINE = "am65xx"

PACKAGE_ARCH = "${MACHINE_ARCH}"

TARGET = " \
    am65x-pru0-prueth-fw.elf \
    am65x-pru1-prueth-fw.elf \
    am65x-rtu0-prueth-fw.elf \
    am65x-rtu1-prueth-fw.elf \
"

do_install() {
	install -d ${D}${nonarch_base_libdir}/firmware/ti-pruss
	for f in ${TARGET}; do
		install -m 0644 ${S}/ti-pruss/$f ${D}${nonarch_base_libdir}/firmware/ti-pruss/$f
	done
}
