SUMMARY = "PRU HSR firmware"

require recipes-bsp/ti-linux-fw/ti-linux-fw.inc

PE = "1"
PR = "${INC_PR}.0"

COMPATIBLE_MACHINE = "ti33x|ti43x|am57xx|am65xx|am64xx"

TARGET = " \
    am335x-pru0-pruhsr-fw.elf \
    am335x-pru1-pruhsr-fw.elf \
    am437x-pru0-pruhsr-fw.elf \
    am437x-pru1-pruhsr-fw.elf \
    am57xx-pru0-pruhsr-fw.elf \
    am57xx-pru1-pruhsr-fw.elf \
    am65x-sr2-pru0-pruhsr-fw.elf \
    am65x-sr2-pru1-pruhsr-fw.elf \
    am65x-sr2-rtu0-pruhsr-fw.elf \
    am65x-sr2-rtu1-pruhsr-fw.elf \
    am65x-sr2-txpru0-pruhsr-fw.elf \
    am65x-sr2-txpru1-pruhsr-fw.elf \
    am64x-sr2-pru0-pruhsr-fw.elf \
    am64x-sr2-pru1-pruhsr-fw.elf \
    am64x-sr2-rtu0-pruhsr-fw.elf \
    am64x-sr2-rtu1-pruhsr-fw.elf \
    am64x-sr2-txpru0-pruhsr-fw.elf \
    am64x-sr2-txpru1-pruhsr-fw.elf \
"

do_install() {
	install -d ${D}${nonarch_base_libdir}/firmware/ti-pruss
	for f in ${TARGET}; do
		install -m 0644 ${S}/ti-pruss/$f ${D}${nonarch_base_libdir}/firmware/ti-pruss/$f
	done
}
