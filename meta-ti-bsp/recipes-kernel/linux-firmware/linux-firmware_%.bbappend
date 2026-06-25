PR:append:ti-soc = ".tisoc1"

# meta-ti-bsp packages newer versions on these
do_install:append:ti-soc() {
	rm -rf ${D}${nonarch_base_libdir}/firmware/ti-connectivity/
	rm -rf ${D}${nonarch_base_libdir}/firmware/cadence/
}
