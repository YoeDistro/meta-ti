SUMMARY = "TI SYSFW/TIFS Firmware"

inherit deploy

require recipes-bsp/ti-linux-fw/ti-linux-fw.inc

PACKAGE_ARCH = "${MACHINE_ARCH}"

PV = "${TI_SYSFW_VERSION}"
PR = "${INC_PR}.0"

do_install() {
	install -d ${D}${nonarch_base_libdir}/firmware/ti-sysfw
	install -m 644 ${S}/ti-sysfw/ti-sci-firmware-* ${D}${nonarch_base_libdir}/firmware/ti-sysfw
	install -m 644 ${S}/ti-sysfw/ti-fs-firmware-* ${D}${nonarch_base_libdir}/firmware/ti-sysfw
	install -m 644 ${S}/ti-sysfw/ti-fs-stub-firmware-* ${D}${nonarch_base_libdir}/firmware/ti-sysfw
}

do_deploy(){
}

do_deploy:k3r5() {
	install -d ${DEPLOYDIR}/ti-sysfw
	install -m 644 ${S}/ti-sysfw/ti-sci-firmware-* ${DEPLOYDIR}/ti-sysfw
	install -m 644 ${S}/ti-sysfw/ti-fs-firmware-* ${DEPLOYDIR}/ti-sysfw
	install -m 644 ${S}/ti-sysfw/ti-fs-stub-firmware-* ${DEPLOYDIR}/ti-sysfw
}

addtask deploy before do_build after do_compile
