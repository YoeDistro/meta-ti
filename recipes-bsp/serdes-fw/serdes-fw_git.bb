DESCRIPTION = "SerDes firmware for Keystone PCIe and 1/10GigE"

LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://LICENCE.ti-keystone;md5=3a86335d32864b0bef996bee26cc0f2c"

PV = "1.0.0"
PR = "r0"

CLEANBROKEN = "1"

COMPATIBLE_MACHINE = "keystone"

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRCREV = "8756d98081e1011a1334b0769f13ffb31d3b41bd"
BRANCH ?= "ti-linux-firmware-4.1.y"

SRC_URI = "git://git.ti.com/processor-firmware/ti-linux-firmware.git;protocol=git;branch=${BRANCH}"

S = "${WORKDIR}/git"
ORIGINPCIE = "ks2_pcie_serdes_1.0.0.bin"
ORIGINGBE = "ks2_gbe_serdes_1.0.0.bin"
ORIGINXGBE = "ks2_xgbe_serdes_1.0.0.bin"

TARGETPCIE = "ks2_pcie_serdes.bin"
TARGETGBE = "ks2_gbe_serdes.bin"
TARGETXGBE = "ks2_xgbe_serdes.bin"

do_install() {
	install -d ${D}${base_libdir}/firmware
	install -m 0644 ${S}/ti-keystone/${ORIGINPCIE} ${D}${base_libdir}/firmware/${TARGETPCIE}
	install -m 0644 ${S}/ti-keystone/${ORIGINGBE} ${D}${base_libdir}/firmware/${TARGETGBE}
	install -m 0644 ${S}/ti-keystone/${ORIGINXGBE} ${D}${base_libdir}/firmware/${TARGETXGBE}
}

FILES_${PN} = "${base_libdir}/firmware"
