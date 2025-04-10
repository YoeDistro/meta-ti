SUMMARY = "TI HSM demo Firmware"

inherit deploy

require recipes-bsp/ti-linux-fw/ti-linux-fw.inc

PACKAGE_ARCH = "${MACHINE_ARCH}"

PV = "${TI_HSM_DEMO_FW_VERSION}"
PR = "${INC_PR}.0"

COMPATIBLE_MACHINE = "j721s2|j784s4"

HSM_FW_SOC:j721s2 = "j721s2"
HSM_FW_SOC:j784s4 = "j784s4"

HSM_BINARY = "hsm-demo-firmware-${HSM_FW_SOC}*.bin"

do_deploy() {
	install -d ${DEPLOYDIR}/ti-hsm
	install -m 644 ${S}/ti-hsm/${HSM_BINARY} ${DEPLOYDIR}/ti-hsm
}

addtask deploy before do_build after do_compile
