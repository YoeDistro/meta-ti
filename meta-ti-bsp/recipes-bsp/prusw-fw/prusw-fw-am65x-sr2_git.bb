SUMMARY = "PRU Ethernet Switch firmware for AM65xx SR2.0"

require recipes-bsp/ti-linux-fw/ti-linux-fw.inc

PV = "${PRUETH_FW_AM65X_SR2_VERSION}"
PR = "${INC_PR}.0"

COMPATIBLE_MACHINE = "am65xx-evm|am64xx"

PACKAGE_ARCH = "${MACHINE_ARCH}"

TARGET = " \
    am65x-sr2-pru0-prusw-fw.elf \
    am65x-sr2-pru1-prusw-fw.elf \
    am65x-sr2-rtu0-prusw-fw.elf \
    am65x-sr2-rtu1-prusw-fw.elf \
    am65x-sr2-txpru0-prusw-fw.elf \
    am65x-sr2-txpru1-prusw-fw.elf \
"

do_install() {
	install -d ${D}${nonarch_base_libdir}/firmware/ti-pruss
	for f in ${TARGET}; do
		install -m 0644 ${S}/ti-pruss/$f ${D}${nonarch_base_libdir}/firmware/ti-pruss/$f
	done
}
