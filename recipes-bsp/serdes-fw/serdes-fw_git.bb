DESCRIPTION = "SerDes firmware for Keystone PCIe and 1/10GigE"

require recipes-bsp/ti-linux-fw/ti-linux-fw.inc

LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://LICENCE.ti-keystone;md5=3a86335d32864b0bef996bee26cc0f2c"

PV = "${SERDES_FW_VERSION}"
PR = "${INC_PR}.0"

CLEANBROKEN = "1"

COMPATIBLE_MACHINE = "keystone"

PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}/git"
TARGET = "ks2_pcie_serdes.bin ks2_gbe_serdes.bin ks2_xgbe_serdes.bin"

do_install() {
	install -d ${D}${base_libdir}/firmware
	for f in ${TARGET}; do
		install -m 0644 ${S}/ti-keystone/$f ${D}${base_libdir}/firmware/$f
	done
}

FILES_${PN} = "${base_libdir}/firmware"
