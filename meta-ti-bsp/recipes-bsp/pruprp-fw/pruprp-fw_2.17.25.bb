SUMMARY = "PRU PRP firmware for AM335x/AM437x/AM57xx"

require recipes-bsp/ti-linux-fw/ti-linux-fw.inc

PE = "1"
PR = "${INC_PR}.0"

COMPATIBLE_MACHINE = "ti33x|ti43x|am57xx|am64xx"

TARGET = " \
	am335x-pru0-pruprp-fw.elf \
	am335x-pru1-pruprp-fw.elf \
	am437x-pru0-pruprp-fw.elf \
	am437x-pru1-pruprp-fw.elf \
	am57xx-pru0-pruprp-fw.elf \
	am57xx-pru1-pruprp-fw.elf \
	am64x-sr2-pru0-pruprp-fw.elf \
	am64x-sr2-pru1-pruprp-fw.elf \
	am64x-sr2-rtu0-pruprp-fw.elf \
	am64x-sr2-rtu1-pruprp-fw.elf \
	am64x-sr2-txpru0-pruprp-fw.elf \
	am64x-sr2-txpru1-pruprp-fw.elf \
"

do_install() {
	install -d ${D}${nonarch_base_libdir}/firmware/ti-pruss
	for f in ${TARGET}; do
		install -m 0644 ${S}/ti-pruss/$f ${D}${nonarch_base_libdir}/firmware/ti-pruss/$f
	done
}
