SUMMARY = "Cadence MHDP DP bridge firmware"

require recipes-bsp/ti-linux-fw/ti-linux-fw.inc

LICENSE = "BSD-3-Clause | Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENCE.cadence;md5=009f46816f6956cfb75ede13d3e1cee0"

PV = "${CADENCE_MHDP_FW_VERSION}"
PR = "${INC_PR}.0"

CLEANBROKEN = "1"

COMPATIBLE_MACHINE = "j7-evm|j7-hs-evm|j721s2-evm|j721s2-hs-evm|j784s4-evm|j784s4-hs-evm"

PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}/git"
TARGET = "mhdp8546.bin"

do_install() {
	install -d ${D}${base_libdir}/firmware/cadence
	install -m 0644 ${S}/cadence/${TARGET} ${D}${base_libdir}/firmware/cadence/${TARGET}
}

FILES_${PN} = "${base_libdir}/firmware"
