DESCRIPTION = "SerDes firmware for Keystone PCIe and 1/10GigE"

LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://LICENCE.ti-keystone;md5=3a86335d32864b0bef996bee26cc0f2c"

PV = "3.3.0.2c"

CLEANBROKEN = "1"

COMPATIBLE_MACHINE = "keystone"

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRCREV = "8d861bd8f8f792df60b6873989ff208766fbebae"
BRANCH ?= "ti-linux-firmware-4.1.y"

SRC_URI = "git://git.ti.com/processor-firmware/ti-linux-firmware.git;protocol=git;branch=${BRANCH}"

S = "${WORKDIR}/git"
TARGET = "ks2_pcie_serdes.bin ks2_gbe_serdes.bin ks2_xgbe_serdes.bin"

do_install() {
	install -d ${D}${base_libdir}/firmware
	for f in ${TARGET}; do
		install -m 0644 ${S}/ti-keystone/$f ${D}${base_libdir}/firmware/$f
	done
}

FILES_${PN} = "${base_libdir}/firmware"
