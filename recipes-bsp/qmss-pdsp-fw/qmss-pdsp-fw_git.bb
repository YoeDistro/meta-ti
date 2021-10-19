DESCRIPTION = "QMSS PDSP firmware"

require recipes-bsp/ti-linux-fw/ti-linux-fw.inc

LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://LICENCE.ti-keystone;md5=3a86335d32864b0bef996bee26cc0f2c"

PV = "${QMSS_PDSP_FW_VERSION}"
PR = "${INC_PR}.1"

CLEANBROKEN = "1"

COMPATIBLE_MACHINE = "keystone"

PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}/git"
ORIGIN = "ks2_qmss_pdsp_acc48_k2_le_1_0_0_9.bin"
TARGET = "ks2_qmss_pdsp_acc48.bin"

do_install() {
	install -d ${D}${base_libdir}/firmware
	install -m 0644 ${S}/ti-keystone/${ORIGIN} ${D}${base_libdir}/firmware/${TARGET}
}

FILES_${PN} = "${base_libdir}/firmware"
